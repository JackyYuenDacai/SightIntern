//todo: print location
//now:re-organize menu.dart, pop.dart given value will cover value given here?Or not passed?
//question is: how to set up proper empty value?


import 'package:flutter/material.dart';
import './columnWidget.dart';
import './RFIDPage.dart';
import './ManPage.dart';
import './pop.dart';
import './DataPage.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import './I8N.dart';
import 'network_request.dart';
import 'SettingPage.dart';

import 'DataForm.dart';

import 'dart:convert';
import 'package:convert/convert.dart';
import 'package:crypto/crypto.dart';

void main() => runApp(MyApp());

class StaticList{
  static List<ColForm> colform_list = new List<ColForm>();
  static List<DataForm> datform_list = new List<DataForm>();
  static String location = '?';
  static record_entries entries;
  static List<String> student_id = new List<String>();
  static List<String> student_name = new List<String>();

  static List<String> staff_id = new List<String>();
  static List<String> staff_list = new List<String>();

  static List<String> tag_list = new List<String>();

  static String server_addr = 'http://192.168.31.2:8080';//'http://192.168.31.2:8080';
  static String getpop_api_url = server_addr+'/WebInterface/get_pops_list?location=';
  static String getstaff_api_url = server_addr+'/WebInterface/get_staff_list?location=';
  static String submit_form_api_url = server_addr+'/WebInterface/submit_form?';
  static String get_student_list = server_addr+'/WebInterface/get_student_list';
  static String get_record_data_url = server_addr+'/WebInterface/getRecordData?';
  static String get_tags_url = server_addr+'/WebInterface/get_tags_list?location=';
  static String add_student_api_url = server_addr+'/WebInterface/add_student?';
  static String del_student_api_url = server_addr+'/WebInterface/del_student?';
  static String get_record_export_url = server_addr+'/WebInterface/record_export?';
  static String add_staff_api_url = server_addr+'/WebInterface/add_staff?';
  static List<question> QuestionList = <question>[
    new question('Check','diaper',<String>['N/A','Clean','Dirty'],<String>['na','clean','dirty'],0),
    new question('Mistake','mistake',<String>['N/A','Wee','Poo','Both'],<String>['na','wee','poo','both'],0),
    new question('Toilet','toilet',<String>['N/A','Nothing','Wee','Poo','Both'],<String>['na','nothing','wee','poo','both'],0),
    new question('Poo','poo',<String>['N/A','Few','Normal','Much'],<String>['na','few','normal','much'],0),
    new question('Consistency','poo_consistency',<String>['Soft','Hard','Rot','Dilute'],<String>['soft','hard','rot','dilute'],0),
    new question('Color','poo_color',<String>['true','false'],<String>['true','false'],2),
    new question('Yellow','poo_color_yellow',<String>['true','false'],<String>['true','false'],1),
    new question('Brown','poo_color_brown',<String>['true','false'],<String>['true','false'],1),
    new question('Black','poo_color_black',<String>['true','false'],<String>['true','false'],1),
    new question('Blood','poo_consist_blood',<String>['true','false'],<String>['true','false'],1),
    new question('Goo','poo_consist_goo',<String>['true','false'],<String>['true','false'],1),
  ];

}

class MyApp extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'CaritasApp',
      debugShowCheckedModeBanner: false,
      onGenerateTitle: (BuildContext context) => I8N.of(context).apptitle,
      localizationsDelegates: [
        // ... app-specific localization delegate[s] here
        const I8NDelegate(),
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
      ],

      supportedLocales: [
         const Locale('en'), // English
         const Locale('zh'), // Chinese
         const Locale.fromSubtags(languageCode: 'zh'), // generic Chinese 'zh'
         const Locale.fromSubtags(languageCode: 'zh', scriptCode: 'Hans'), // generic simplified Chinese 'zh_Hans'
         const Locale.fromSubtags(languageCode: 'zh', scriptCode: 'Hant'), // generic traditional Chinese 'zh_Hant'
         const Locale.fromSubtags(languageCode: 'zh', scriptCode: 'Hans', countryCode: 'CN'), // 'zh_Hans_CN'
         const Locale.fromSubtags(languageCode: 'zh', scriptCode: 'Hant', countryCode: 'TW'), // 'zh_Hant_TW'
         const Locale.fromSubtags(languageCode: 'zh', scriptCode: 'Hant', countryCode: 'HK'), // 'zh_Hant_HK'
      ],

      theme: ThemeData(
        primarySwatch: Colors.orange,
      ),

      routes: <String, WidgetBuilder>{
          '/RFIDPage':(BuildContext context) => new RFIDPage(),
          '/ManPage':(BuildContext context) => new ManPage(),
          '/DataPage':(BuildContext context) => new DataPage(),
          '/SettingPage':(BuildContext context) => new SettingPage(),
      },
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  TextStyle style = TextStyle(fontFamily: 'Montserrat', fontSize: 20.0);
  final TextEditingController _userFilter = new TextEditingController();
  final TextEditingController _passwordFilter = new TextEditingController();
  String _user = "";
  String _password = "";


  _MyHomePageState() {
    _userFilter.addListener(_userListen);
    _passwordFilter.addListener(_passwordListen);
  }

  void _userListen() {
    if (_userFilter.text.isEmpty) {
      _user = "";
    } else {

      _user = _userFilter.text;
      StaticList.location = _user;
    }
  }

  void _passwordListen() {
    if (_passwordFilter.text.isEmpty) {
      _password = "";
    } else {
      _password = generateMd5(_passwordFilter.text);
    }
  }

  void _loginPressed () {
      //StaticList.location = _userFilter.text;
      print('The user wants to login with $_user and $_password');
  }
  
  @override
  Widget build(BuildContext context) {

    final userField = TextField(
      //obscureText: true,
      controller: _userFilter,
      style: style,
      decoration: InputDecoration(
          contentPadding: EdgeInsets.fromLTRB(20.0, 15.0, 20.0, 15.0),
          hintText: "Username",
          border:
              OutlineInputBorder(borderRadius: BorderRadius.circular(32.0))),
    );

    final passwordField = TextField(
      obscureText: true,
      controller: _passwordFilter,
      style: style,
      decoration: InputDecoration(
          contentPadding: EdgeInsets.fromLTRB(20.0, 15.0, 20.0, 15.0),
          hintText: "Password",
          border:
              OutlineInputBorder(borderRadius: BorderRadius.circular(32.0))),
    );

    final loginButon = Material(
      elevation: 5.0,
      borderRadius: BorderRadius.circular(30.0),
      color: Color(0xffFFA500),
      child: MaterialButton(
        minWidth: MediaQuery.of(context).size.width,
        padding: EdgeInsets.fromLTRB(20.0, 15.0, 20.0, 15.0),
        onPressed: (){
          _loginPressed;
          Navigator.pushNamed(context, '/RFIDPage');
        }, //=> Navigator.pushNamed(context, '/RFIDPage'),
        child: Text("Login",
            textAlign: TextAlign.center,
            style: style.copyWith(
                color: Colors.white, fontWeight: FontWeight.bold)),
      ),
    );


    return Scaffold(
      body: SingleChildScrollView(
      child: Center(
        child: Container(
          color: Colors.white,
          child: Padding(
            padding: const EdgeInsets.all(36.0),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.center,
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                SizedBox(
                  height: 155.0,
                  child: Image.asset(
                    "assets/logo.png",
                    fit: BoxFit.contain,
                  ),
                ),
                SizedBox(height: 45.0),
                userField,
                SizedBox(height: 25.0),
                passwordField,
                SizedBox(
                  height: 35.0,
                ),
                loginButon,
                SizedBox(
                  height: 15.0,
                ),
              ],
            ),
          ),
        ),
      ),
        )
    );
  }
}

String generateMd5(String data) {
  var content = new Utf8Encoder().convert(data);
  var digest = md5.convert(content);
  // 这里其实就是 digest.toString()
  return hex.encode(digest.bytes);
}