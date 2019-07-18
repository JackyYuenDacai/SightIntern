library menu;
import 'package:flutter/material.dart';
import './I8N.dart';

class MyMenu_RFID extends StatelessWidget{
  Widget userHeader = UserAccountsDrawerHeader(
      accountName: new Text('Location'),
      currentAccountPicture: new CircleAvatar(
        backgroundImage: AssetImage('images/pic1.jpg'), radius: 35.0,),);
  @override
  Widget build(BuildContext context){
    return ListView(
          padding: EdgeInsets.zero,
          children: <Widget>[
            userHeader ,
            ListTile(title: Text(I8N.of(context).rfid_title),
              leading: new CircleAvatar(child: new Icon(Icons.school),),
              onTap: () {
               // Navigator.of(context).pushNamed('/RFIDPage');
              },),
            ListTile(title: Text(I8N.of(context).students_title),
              leading: new CircleAvatar(child: new Icon(Icons.school),),
              onTap: () {
                Navigator.of(context).pushNamed('/DataPage');
              },),
            ListTile(title: Text(I8N.of(context).manuel_title),
              leading: new CircleAvatar(child: new Text('B2'),),
              onTap: () {
                //Navigator.pop(context);
                Navigator.of(context).pushNamed('/ManPage');
              },),
            /*ListTile(title: Text(I8N.of(context).setting_title),
              leading: new CircleAvatar(
                child: new Icon(Icons.list),),
              onTap: () {
                                Navigator.of(context).pushNamed('/SettingPage');
              },),*/
          ],
        );
  }
}

class MyMenu_Data extends StatelessWidget{
  Widget userHeader = UserAccountsDrawerHeader(
      accountName: new Text('Location'),
      currentAccountPicture: new CircleAvatar(
        backgroundImage: AssetImage('images/pic1.jpg'), radius: 35.0,),);
  @override
  Widget build(BuildContext context){
    return ListView(
          padding: EdgeInsets.zero,
          children: <Widget>[
            userHeader ,
            ListTile(title: Text(I8N.of(context).rfid_title),
              leading: new CircleAvatar(child: new Icon(Icons.school),),
              onTap: () {
                Navigator.of(context).pushNamed('/RFIDPage');
              },),
            ListTile(title: Text(I8N.of(context).students_title),
              leading: new CircleAvatar(child: new Icon(Icons.school),),
              onTap: () {
                //Navigator.of(context).pushNamed('/DataPage');
              },),
            ListTile(title: Text(I8N.of(context).manuel_title),
              leading: new CircleAvatar(child: new Text('B2'),),
              onTap: () {
                //Navigator.pop(context);
                Navigator.of(context).pushNamed('/ManPage');
              },),
            /*ListTile(title: Text(I8N.of(context).setting_title),
              leading: new CircleAvatar(
                child: new Icon(Icons.list),),
              onTap: () {
                                Navigator.of(context).pushNamed('/SettingPage');
              },),*/
          ],
        );
  }
}

class MyMenu_Manual extends StatelessWidget{
  Widget userHeader = UserAccountsDrawerHeader(
      accountName: new Text('Location'),
      currentAccountPicture: new CircleAvatar(
        backgroundImage: AssetImage('images/pic1.jpg'), radius: 35.0,),);
  @override
  Widget build(BuildContext context){
    return ListView(
          padding: EdgeInsets.zero,
          children: <Widget>[
            userHeader ,
            ListTile(title: Text(I8N.of(context).rfid_title),
              leading: new CircleAvatar(child: new Icon(Icons.school),),
              onTap: () {
                Navigator.of(context).pushNamed('/RFIDPage');
              },),
            ListTile(title: Text(I8N.of(context).students_title),
              leading: new CircleAvatar(child: new Icon(Icons.school),),
              onTap: () {
                Navigator.of(context).pushNamed('/DataPage');
              },),
            ListTile(title: Text(I8N.of(context).manuel_title),
              leading: new CircleAvatar(child: new Text('B2'),),
              onTap: () {
                //Navigator.pop(context);
                //Navigator.of(context).pushNamed('/ManPage');
              },),
            /*ListTile(title: Text(I8N.of(context).setting_title),
              leading: new CircleAvatar(
                child: new Icon(Icons.list),),
              onTap: () {
                                Navigator.of(context).pushNamed('/SettingPage');
              },),*/
          ],
        );
  }
}