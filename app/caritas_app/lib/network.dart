//TODO: add parameters for request_basic__send!
//TODO: check new change of API : find records by id? if_form?

import 'pop.dart';
import 'dart:async';
import 'package:async/async.dart';
import 'dart:convert';
import 'dart:io';
import 'package:http/http.dart' as http;
import 'columnWidget.dart';
import 'package:json_annotation/json_annotation.dart';
import './main.dart';
import './json.dart';

typedef ProcessFunc = void Function(http.Response);

class connection{

  static var client = new http.Client();
  static http.Response ajaxResponse = new http.Response("",200);
  static String full_server_addr = 'http://35.200.70.172:8888/WebInterface';
  
  static String token_update = full_server_addr+'/api/token_update';
  static String login = full_server_addr+'/api/login';
  static String records = full_server_addr+'/api/records';
  static String form_config = full_server_addr+'/api/form_config';

    ///what is it doing? Send request to url, call function (process) with response given as parameter///
  static void requestWrap(String url,ProcessFunc process) async{

    await http.get(url)
        .then((response) {
          if(response.body.length<=0){
            return;
          }
          if(response.statusCode != 200){
            print('Server exception');
            print(response.body);
            return;
          }
          ajaxResponse = response;
          process(response);
    });
  }

  ///what is it doing? Post something to the api, call function (process) with response given as parameter///
  static void postWrap(String url,var header,var body_data, ProcessFunc process) async{
    print(body_data);
    print(url);
    await http.post(
      url,
      body:body_data,
      headers:header
    ).then((response) {
      if(response.body.length<=0){
        return;
      }
      if(response.statusCode != 200){
        print('Server exception');
        print(response.body);
        return;
      }
      ajaxResponse = response;
      process(response);
    });
  }

  static void get_staff_list(String location){
    records_send filter = new records_send(location, null, 2, null, null, null, null, null, null);
    request_basic_send data = new request_basic_send(token, StaticList.location, StaticList.soc, jsonEncode(filter));
    String url = records;

    postWrap(url,{
        HttpHeaders.contentTypeHeader: "application/json",
        "callMethod" : "DOCTOR_AVAILABILITY"
      },
      jsonEncode(data),
      (response)=>get_staff_list_proc(response)
    );  
  }

  static void get_staff_list_proc(http.Response response){
    //String sResp = String(response);
    Map basicMap = jsonDecode(response.body);
    var basic_response = new request_basic_receive.fromJson(basicMap);
    Map listMap = jsonDecode(basic_response.data);
    var user_list = new record_list_content.fromJson(listMap);
    Map userMap = jsonDecode(user_list);
    var list = new record_list_content.fromJson(userMap);
    //staffList staffs = new staffList(Staffs: new List(2).add(value));
    StaticList.staff_id.clear();
    StaticList.staff_list.clear();
    for(int i = userMap.length;i>0;i--){
        staff wid = new staff(list.id, list.name);
        StaticList.staff_id.add(wid.id);
        print('name:'+wid.name);
        StaticList.staff_list.add(wid.name);
    }
  }

  static void get_pop_list(String location){
    records_send filter = new records_send(location, null, 4, null, null, null, null, null, null);
    request_basic_send data = new request_basic_send(StaticList.token, StaticList.location, StaticList.soc, jsonEncode(filter));
    String url = records;

    postWrap(url,{
        HttpHeaders.contentTypeHeader: "application/json",
        "callMethod" : "DOCTOR_AVAILABILITY"
      },
      jsonEncode(data),
      (response)=>get_staff_list_proc(response)
    );  
  }

  static void get_pop_list_proc(http.Response response){
    Map basicMap = jsonDecode(response.body);
    var basic_response = new request_basic_receive.fromJson(basicMap);
    Map listMap = jsonDecode(basic_response.data);
    var user_list = new record_received.fromJson(listMap);
    Map userMap = jsonDecode(user_list);
    var list = new record_list_content.fromJson(userMap);
    //staffList staffs = new staffList(Staffs: new List(2).add(value));
    /*StaticList.staff_id.clear();
    StaticList.staff_list.clear();*/
    for(int i = userMap.length;i>0;i--){
        /*staff wid = new staff(list.id, list.name);
        StaticList.staff_id.add(wid.id);
        print('name:'+wid.name);
        StaticList.staff_list.add(wid.name);*/
        
        if(list.time_end == null){
            StaticList.colform_list.add(new ColForm(list.name,list.id,list.record_token));
            print("added colform ${list.name} ${list.id} ${list.record_token}");
        }else if(list.time_end != null){
          for(var obj in StaticList.colform_list){
            if(obj.name == list.name){
              StaticList.colform_list.remove(obj);
              print("deleted colform ${list.name} ${list.id} ${list.record_token}");
              break;
            }
          }
        } 
    }
  }
  //possible solution: use /records to find name first
  static void get_record_data(String id, String time)async{
    /*var url = StaticList.get_record_data_url+"id="+id+"&time=${time}";
    await requestWrap(url,(response)=>get_record_data_proc(response));*/

    records_send filter = new records_send(null, null, 4, null, null, time, null, id, null);
    request_basic_send data = new request_basic_send(token, id, soc, jsonEncode(filter));
    String url = records;

    postWrap(url,{
        HttpHeaders.contentTypeHeader: "application/json",
        "callMethod" : "DOCTOR_AVAILABILITY"
      },
      jsonEncode(data),
      (response)=>get_record_data_proc(response)
    );
  }

  static void get_record_data_proc(http.Response response){
    Map basicMap = jsonDecode(response.body);
    var basic_response = new request_basic_receive.fromJson(basicMap);
    Map listMap = jsonDecode(basic_response.data);
    var user_list = new record_list_content.fromJson(listMap);
    Map userMap = jsonDecode(user_list);
    var list = new record_list_content.fromJson(userMap);
    //staffList staffs = new staffList(Staffs: new List(2).add(value));
    
    StaticList.entries.add(new record_entry(list.time_start, list.duration));//record_entries(list.time_start,)
  //Location for record?
  }

  static void post_submit_form(String id,String unitok,Map<String,String> json_data){
    String url = form_config;
    url = url + 'type=add&';
    url = url + 'id=' + id +'&';
    url = url + 'soc=Caritas&'; 
    postWrap(url,{
        HttpHeaders.contentTypeHeader: "application/json",
        "callMethod" : "DOCTOR_AVAILABILITY"
      },
      json.encode(json_data),
      (response){
        if(response.body == 'success'){
          for(ColForm a in StaticList.colform_list){
            if(a.id == id){
              StaticList.colform_list.remove(a);
              break;
            }
          }
        }
        ;
      });
  }

  static void get_token(String location){
    String url = token_update;

    /*postWrap(url,{
        HttpHeaders.contentTypeHeader: "application/json",
        "callMethod" : "DOCTOR_AVAILABILITY"
      },
      //jsonEncode(data),
      (response)=>get_token_proc(response)
    );*/

    http.get(url)
          .then((response) {
            //print("Submit Response status: ${response.statusCode}");
            print("Submit: ${response.body}");
            if(response.body.length>0){
              StaticList.token = response.body;
            }
          });
  }

  static void send_login(String soc, String id, String pwd){
    String url = login;
    login_send json_data = new login_send(id, soc, pwd);
    postWrap(url,{
        HttpHeaders.contentTypeHeader: "application/json",
        "callMethod" : "DOCTOR_AVAILABILITY"
      },
      jsonEncode(json_data),
      (response)=>send_login_proc(response)
    );  
  }

  static void send_login_proc(http.Response response){
    Map basicMap = jsonDecode(response.body);
    var response_json = new request_basic_receive.fromJson(basicMap);
    if (response_json.error == 0){
      Map tokenMap = jsonDecode(response_json.data);
      var token_pack = new login_receive.fromJson(tokenMap);
      StaticList.token = token_pack.token;
      StaticList.login_status = 1;
    }
    else StaticList.login_status = 2;
  }
}
