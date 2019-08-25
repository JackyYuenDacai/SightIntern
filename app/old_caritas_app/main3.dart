//import 'package:flutter/material.dart';

/*void main() => runApp(new MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: 'Flutter Simple Login Demo',
      theme: new ThemeData(
        primarySwatch: Colors.blue
      ),
      home: new LoginPage(),
    );
  }
}*/

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

/*class StaticList{
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

}*/

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
      home: new LoginPage(),
    );
  }
}

String generateMd5(String data) {
  var content = new Utf8Encoder().convert(data);
  var digest = md5.convert(content);
  // 这里其实就是 digest.toString()
  return hex.encode(digest.bytes);
}

class LoginPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => new _LoginPageState();
}

// Used for controlling whether the user is loggin or creating an account
enum FormType {
  login,
  register
}

class _LoginPageState extends State<LoginPage> {
  TextStyle style = TextStyle(fontFamily: 'Montserrat', fontSize: 20.0);
  final TextEditingController _emailFilter = new TextEditingController();
  final TextEditingController _passwordFilter = new TextEditingController();
  String _email = "";
  String _password = "";
  FormType _form = FormType.login; // our default setting is to login, and we should switch to creating an account when the user chooses to

  _LoginPageState() {
    _emailFilter.addListener(_emailListen);
    _passwordFilter.addListener(_passwordListen);
  }

  void _emailListen() {
    if (_emailFilter.text.isEmpty) {
      _email = "";
    } else {
      _email = _emailFilter.text;
    }
  }

  void _passwordListen() {
    if (_passwordFilter.text.isEmpty) {
      _password = "";
    } else {
      _password = _passwordFilter.text;
    }
  }

  // Swap in between our two forms, registering and logging in
  void _formChange () async {
    setState(() {
      if (_form == FormType.register) {
        _form = FormType.login;
      } else {
        _form = FormType.register;
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: _buildBar(context),
      body: new Container(
        padding: EdgeInsets.all(16.0),
        child: new Column(
          children: <Widget>[
            SizedBox(
                  height: 155.0,
                  child: Image.asset(
                    "assets/logo.png",
                    fit: BoxFit.contain,
                  ),
                ),
                SizedBox(height: 45.0),
                _buildTextFields(),
                SizedBox(
                  height: 35.0,
                ),
                _buildButtons(),
                SizedBox(
                  height: 15.0,
                ),
            
            /*_buildTextFields(),
            _buildButtons(),*/
          ],
        ),
      ),
    );
  }

  Widget _buildBar(BuildContext context) {
    return new AppBar(
      title: new Text(""),
      centerTitle: true,
    );
  }
  /*Widget _buildLogo(){
    return new Scaffold(
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
                )
              ]
            )
          )
        )
      )
    )
    ); 
  }*/


  Widget _buildTextFields() {
    return new Container(
      child: new Column(
        children: <Widget>[
          new Container(
            child: new TextField(
              controller: _emailFilter,
              style: style,
              decoration: InputDecoration(
                contentPadding: EdgeInsets.fromLTRB(20.0, 15.0, 20.0, 15.0),
                hintText: "Username",
                border:
                  OutlineInputBorder(borderRadius: BorderRadius.circular(32.0))),
            ),
          ),
          new Container(
            child: new TextField(
              controller: _passwordFilter,
              obscureText: true,
              style: style,
              decoration: InputDecoration(
                contentPadding: EdgeInsets.fromLTRB(20.0, 15.0, 20.0, 15.0),
                hintText: "Password",
                border:
                  OutlineInputBorder(borderRadius: BorderRadius.circular(32.0))),
            )
          )
        ],
      ),
    );
  }

  Widget _buildButtons() {
    //if (_form == FormType.login) {
      return new Container(
        child: new Column(
          children: <Widget>[
            new Material(
              elevation: 5.0,
              borderRadius: BorderRadius.circular(30.0),
              color: Color(0xffFFA500),
              child: MaterialButton(
                minWidth: MediaQuery.of(context).size.width,
                padding: EdgeInsets.fromLTRB(20.0, 15.0, 20.0, 15.0),
                onPressed: (){
                  StaticList.location = _email;
                  _password = generateMd5(_password);
                  print('The user wants to login with $_email and $_password');
                  Navigator.pushNamed(context, '/RFIDPage');
                },
                child: Text("Login",
                  textAlign: TextAlign.center,
                  style: style.copyWith(
                  color: Colors.white, fontWeight: FontWeight.bold)),
              ),
            ),
          ],
        ),
      );
    /*} else {
      return new Container(
        child: new Column(
          children: <Widget>[
            new RaisedButton(
              child: new Text('Create an Account'),
              onPressed: _createAccountPressed,
            ),
            new FlatButton(
              child: new Text('Have an account? Click here to login.'),
              onPressed: _formChange,
            )
          ],
        ),
      );
    }*/
  }
}