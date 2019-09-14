import 'package:caritas_app/DataForm.dart';

import 'pop.dart';
import 'dart:async';
import 'package:async/async.dart';
import 'dart:convert';
import 'dart:io';
import 'package:http/http.dart' as http;
import 'columnWidget.dart';
import 'package:json_annotation/json_annotation.dart';
import './main.dart';

/*class a {
  String token;
  String id;
  String soc;
  String parameters;

  a({this.token, this.id, this.soc, this.parameters});

  a.fromJson(Map<String, dynamic> json) {
    token = json['token'];
    id = json['id'];
    soc = json['soc'];
    parameters = json['parameters'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['token'] = this.token;
    data['id'] = this.id;
    data['soc'] = this.soc;
    data['parameters'] = this.parameters;
    return data;
  }
}*/

class request_basic_send {
  /*final String name;
  final String email;*/
  final String token;
  final String id;
  final String soc;
  final String parameters;

  request_basic_send(this.token, this.id, this.soc, this.parameters);

  Map<String, dynamic> toJson() =>
    {
      'token': token,
      'id': id,
      'soc': soc,
      'parameters': parameters 
    };
}

class request_basic_receive {
  final int error;
  final String error_msg;
  final String data;

  request_basic_receive(this.error, this.error_msg, this.data);

  request_basic_receive.fromJson(Map<String, dynamic> json)
      : error = json['error'],
        error_msg = json['error_msg'],
        data = json['data'];
}

class submit_send {
  final String record_token;
  final List<String> form;

  submit_send(this.record_token, this.form);

  Map<String, dynamic> toJson() =>
    {
      'record_token': record_token,
      'form': form 
    };
}

class records_send {
  final String location;
  final String name;
  final int role;
  final int status;
  final bool registered;
  final DateTime time_start;
  final DateTime time_end;
  final String id;
  final String if_form;

  records_send(this.location, this.name, this.role, this.status, this.registered, this.time_start, this.time_end, this.id, this.if_form);

  Map<String, dynamic> toJson() =>
    {
      'location': location,
      'name': name,
      'role': role,
      'status': status,
      'registered': registered,
      'time_start': time_start,
      'time_end': time_end, 
      'id': id,
      'if_form': if_form
    };
}

class record_received {
  final List<record_list_content> record_list;

  record_received({this.record_list,});

  /*record_receive.fromJson(Map<String, dynamic> json)
      : record_list = json[null];*/

  factory record_received.fromJson(List<dynamic> parsedJson) {

    List<record_list_content> record_list = new List<record_list_content>();
    record_list = parsedJson.map((i)=>record_list_content.fromJson(i)).toList();

    return new record_received(
      record_list: record_list
    );
  }
}

/*class record_list_content {
  final String id;
  final String name;
  final String tag_id;
  final DateTime time_start;
  final DateTime time_end;
  final Duration duration;
  final String record_token;

  record_list_content(this.id, this.name, this.tag_id, this.time_start, this.time_end, this.duration, this.record_token);

  record_list_content.fromJson(Map<String, dynamic> json)
      : id = json['id'],
        name = json['name'],
        tag_id = json['tag_id'],
        time_start = json['time_start'],
        time_end = json['time_end'],
        duration = json['duration'],
        record_token = json['record_token'];
}*/

class record_list_content {
  final String id;
  final String name;
  final String tag_id;
  final DateTime time_start;
  final DateTime time_end;
  final DateTime duration;
  final String record_token;

  record_list_content({this.id, this.name, this.tag_id, this.time_start, this.time_end, this.duration, this.record_token});

  factory record_list_content.fromJson(Map<String, dynamic> json){
    return new record_list_content(
      id: json['id'],
      name: json['name'],
      tag_id: json['tag_id'],
      time_start: json['time_start'],
      time_end: json['time_end'],
      duration: json['duration'],
      record_token: json['record_token'],
    );
  }

}

/*class token_update_received {
  final String token;
  final String name;
  final int privilege;

  token_update_received(this.token, this.name, this.privilege);

  token_update_received.fromJson(Map<String, dynamic> json)
      : token = json['token'],
        name = json['name'],
        privilege = json['privilege'];
}*/

class login_send {
  final String id;
  final String soc;
  final String pwd;

  login_send(this.id, this.soc, this.pwd);

  Map<String, dynamic> toJson() =>
    {
      'id': id,
      'soc': soc,
      'pwd': pwd 
    };
}

class login_receive {
  final String token;

  login_receive(this.token);

  login_receive.fromJson(Map<String, dynamic> json)
      : token = json['token'];
}

class form_config_send {
  final String type;
  final String id;
  final String soc;
  final List<String> form;


  form_config_send(this.type, this.id, this.soc, this.form);

  Map<String, dynamic> toJson() =>
    {
      'type': type,
      'id': id,
      'soc': soc,
      'form': form
    };
}