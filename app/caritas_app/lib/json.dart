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
  final Map<String, dynamic> parameters;

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

  records_send(this.location, this.name, this.role, this.status, this.registered, this.time_start, this.time_end);

  Map<String, dynamic> toJson() =>
    {
      'location': location,
      'name': name,
      'role': role,
      'status': status,
      'registered': registered,
      'time_start': time_start,
      'time_end': time_end 
    };
}

class record_receive {
  final List<String> record_list;

  record_receive(this.record_list);

  record_receive.fromJson(Map<String, dynamic> json)
      : record_list = json[''];
}

class record_list_content {
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
}

