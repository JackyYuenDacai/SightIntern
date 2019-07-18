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
        primarySwatch: Colors.blue,
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

class Navigate extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(
        child: RaisedButton(
            child: Text("Login"),
            onPressed: () => Navigator.pushNamed(context, '/RFIDPage'),
        ),
    );
  }
}

class MyHomePage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new _MyHomePageState();
  }
}

class _MyHomePageState extends State<MyHomePage> {

  @override
  Widget build(BuildContext context) {
    return
    new WillPopScope(
      onWillPop: () async {
        return true;
      },
      child: Scaffold(appBar: new AppBar(title: Text(I8N.of(context).home),),
        body: Center(
          child: Container(
            child: new Column(
              children: <Widget>[
                new Container(
                  child: new TextField(
                    decoration: new InputDecoration(
                      labelText: 'User Name'
                    ),
                  ),
                ),
                new Container(
                  child: new TextField(
                    decoration: new InputDecoration(
                      labelText: 'Password'
                    ),
                    obscureText: true,
                  ),
                ),
                new Navigate()
              ],
            ),
          ),
        ),    
      ),
    );
  }
}