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
import 'dart:convert';
import 'package:convert/convert.dart';
import 'package:crypto/crypto.dart';

void main() => runApp(MyApp());

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
  return hex.encode(digest.bytes);
}

class LoginPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => new _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  TextStyle style = TextStyle(fontFamily: 'Montserrat', fontSize: 20.0);
  final TextEditingController _userFilter = new TextEditingController();
  final TextEditingController _passwordFilter = new TextEditingController();
  String _user = "";
  String _password = "";

  _LoginPageState() {
    _userFilter.addListener(_userListen);
    _passwordFilter.addListener(_passwordListen);
  }

  void _userListen() {
    if (_userFilter.text.isEmpty) {
      _user = "";
    } else {
      _user = _userFilter.text;
    }
  }

  void _passwordListen() {
    if (_passwordFilter.text.isEmpty) {
      _password = "";
    } else {
      _password = _passwordFilter.text;
    }
  }

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: _buildBar(context),
      body: new SingleChildScrollView(
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
            SizedBox(height: 35.0),
            _buildButtons(),
            SizedBox(height: 15.0),
          ],
        ),
      ),
    );
  }

  Widget _buildBar(BuildContext context) {
    return new AppBar(
      title: new Text(I8N.of(context).title),
      centerTitle: true,
    );
  }

  Widget _buildTextFields() {
    return new Container(
      child: new Column(
        children: <Widget>[
          new Container(
            child: new TextField(
              controller: _userFilter,
              style: style,
              decoration: InputDecoration(
                contentPadding: EdgeInsets.fromLTRB(20.0, 15.0, 20.0, 15.0),
                hintText: I8N.of(context).username,
                border:
                  OutlineInputBorder(borderRadius: BorderRadius.circular(32.0))),
            ),
          ),
          new Container(
            child: new TextField(
              controller: _passwordFilter,
              obscureText: true,//*****need to be checked if works with iOS*****
              style: style,
              decoration: InputDecoration(
                contentPadding: EdgeInsets.fromLTRB(20.0, 15.0, 20.0, 15.0),
                hintText: I8N.of(context).password,
                border:
                  OutlineInputBorder(borderRadius: BorderRadius.circular(32.0))),
            )
          )
        ],
      ),
    );
  }

  Widget _buildButtons() {
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
                  StaticList.location = _user;
                  _password = generateMd5(_password);
                  print('The user wants to login with $_user and $_password');
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
  }
}