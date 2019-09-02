import 'package:flutter/material.dart';
import 'package:connectivity/connectivity.dart';
import 'package:flutter_datetime_picker/flutter_datetime_picker.dart';
import 'dart:async';
import 'package:flutter_localizations/flutter_localizations.dart';
import './I8N.dart';
import './pop.dart';
import './menu.dart';
import './main.dart';
import 'RFIDPage.dart';
import 'network_request.dart';

class ManPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new _ManPageState();
  }
}

class _ManPageState extends State<ManPage> {

  var subscription = Connectivity().onConnectivityChanged.listen((ConnectivityResult result) async{
        var connectivityResult = await Connectivity().checkConnectivity();
        if(connectivityResult != ConnectivityResult.mobile && connectivityResult != ConnectivityResult.wifi){
          //NO NETWORK CONNECTION
        }
    });
  initState(){
    super.initState();

  }
  submitClicked(BuildContext context){
    showDialog(
        context: context,
        builder: (context) {
          return new AlertDialog(
            title: new Text("Submit？"),
            content: new Text('  '),
            actions: <Widget>[
              new FlatButton(
                onPressed: () {
                  Navigator.of(context).pop();


                  this.setState((){
                    //this.dispose();

                    //Navigator.of(context).pushNamed('/DataPage');
                  });
                  Navigator.of(context).pushNamed('/DataPage');

                  
                  if(!RFIDPage.IsNetwork){
                    print('no network');
                  }else{
                    network_request.post_submit_form(id,unitok,answer); //////////
                  
                
      setState((){});
    }
  

                },
                child: new Text("Confirm"),
              ),
              new FlatButton(
                onPressed: () {
                  Navigator.of(context).pop();
                },
                child: new Text("Cancel"),
              ),
            ],
          );
        });
  }
  Map<String,String> answer = new Map<String,String>();
  answerSelected(String title,String value){
    //print(title);
    //print(value);
    for(question i in StaticList.QuestionList){
      if(i.title == title){
        for(int j = 0; j < i.answer.length;j++){
          if(i.answer[j] == value){
            answer[i.id] = i.answer_id[j];
            print(i.answer_id[j]);
          }
        }
      }
    }
  }
  String answerValue(String title){
    for(question i in StaticList.QuestionList){
      if(i.title == title){
        //print(i.id);
        for(int j = 0; j < i.answer.length;j++){
          if(i.answer_id[j] == answer[i.id]){
            return i.answer[j];
          }
        }
      }
    }
    return null;
  }
  @override
  Widget build(BuildContext context) {
    return new WillPopScope(
  onWillPop: () async {
    return true;
  },
  child: Scaffold(
      appBar: AppBar(title: Text(I8N.of(context).manuel_title),),
      body:
      new Container(
          margin: const EdgeInsets.only(top:40.0, left: 20.0, right: 20.0),
          child:  ListView(

                        children: <Widget>[
                          new Wrap(
                            direction: Axis.horizontal,
                            children:
                              <Widget>[
                                new Text(I8N.of(context).student_name,textAlign:TextAlign.center,
                                style:TextStyle(
                                  fontSize:20.0,
                                )
                              ),
                                new TextField(
                                  decoration: InputDecoration(
                                    border: InputBorder.none,
                                    hintText: I8N.of(context).student_hint,
                                  ),
                                ),
                              ]
                          ),
                          new Wrap(
                            direction: Axis.horizontal,
                            children:
                              <Widget>[
                                new Text(I8N.of(context).staff_name,textAlign:TextAlign.center,
                                style:TextStyle(
                                  fontSize:20.0,
                                )),
                                new TextField(
                                  decoration: InputDecoration(
                                    border: InputBorder.none,
                                    hintText: I8N.of(context).staff_hint,
                                  ),
                                ),
                              ]
                          ),
                          new Wrap(
                            direction: Axis.horizontal,
                            children:
                            <Widget>[
                                    FlatButton(
                                      onPressed: () {
                                          DatePicker.showDateTimePicker(context,
                                                showTitleActions: true,
                                                onChanged: (date) {
                                              print('change $date');
                                            }, onConfirm: (date) {
                                              print('confirm $date');
                                            }, currentTime: DateTime.now(), locale: LocaleType.zh);
                                      },
                                      child: Text(
                                          I8N.of(context).in_time,
                                          style: TextStyle(color: Colors.orangeAccent,fontSize:25.0,),
                                      )),
                                  FlatButton(
                                    onPressed: () {
                                        DatePicker.showDateTimePicker(context,
                                                              showTitleActions: true,
                                                              onChanged: (date) {
                                                            print('change $date');
                                                          }, onConfirm: (date) {
                                                            print('confirm $date');
                                                          }, currentTime: DateTime.now(), locale: LocaleType.zh);
                                    },
                                    child: Text(
                                        I8N.of(context).out_time,
                                        style: TextStyle(color: Colors.orangeAccent,fontSize:25.0,),
                                    )),
                            ]
                          ),
                          Align(alignment: Alignment.topLeft,child:
                          new Column(children:
                            //question(this.title,this.answer,this.answer_id);
                          StaticList.QuestionList.map((question list_val){
                            switch(list_val.type){
                              case null:
                              case 0:
                                  return Align(alignment: Alignment.topLeft,child:new Column(children:<Widget>[
                                    new SizedBox(height:5),
                                    new DropdownButton<String>(
                                      hint: Container(
                                        width:180.0,
                                        child:Text(list_val.title)
                                      ),
                                      value: answerValue(list_val.title) ?? null,
                                      items: list_val.answer.map((String value) {
                                        return new DropdownMenuItem<String>(
                                        value: value,
                                        child: new Text(value, textAlign: TextAlign.left,style:  TextStyle(
                                                          color: Colors.orangeAccent,
                                                          fontSize: 20.0,
                                                        )),
                                                      );
                                                    }).toList(),
                                        onChanged: (String value_clicked) {
                                          setState(() {
                                              answerSelected(list_val.title,value_clicked);
                                            }
                                          );
                                        },
                                        style:  TextStyle(
                                              color: Colors.orangeAccent,
                                              fontSize: 20.0,
                                              )
                                            ),
                                          ]
                                      ));
                                      break;
                              case 1:
                                  return new Column(
                                        children: <Widget>[
                                          new CheckboxListTile(
                                            title: new Text(list_val.title,
                                              style: TextStyle(
                                                color:Colors.orangeAccent,
                                                fontSize:17.0,
                                              )
                                            ),
                                            controlAffinity: ListTileControlAffinity.leading,
                                            activeColor: Colors.green,
                                            value: (((answerValue(list_val.title)) == 'true')? true:false) ,
                                            onChanged: (bool value) {
                                              setState((){
                                                if(answerValue(list_val.title) == 'true'){
                                                  answerSelected(list_val.title,'false');
                                                }else{
                                                  answerSelected(list_val.title,'true');
                                                }

                                              });
                                              print(answer);

                                            },
                                          ),

                                        ],
                                  );
                                  break;
                              case 2:
                                  return new Text(
                                    list_val.title,
                                    style: TextStyle(
                                      color: Colors.white,
                                      fontSize:20.0,
                                    )
                                  );
                                  break;
                            }

                              }
                            ).toList()
                          )),
                          new SizedBox(height:10),
                          new RaisedButton(child: Text(I8N.of(context).submit_text,
                          style:  TextStyle(
                                      color: Colors.white,
                                      fontSize: 40.0,
                                    )),
                            color: Theme.of(context).accentColor,
                            elevation: 4.0,
                            splashColor: Colors.blueGrey,
                            onPressed:(){submitClicked(context);},),]

                      ),
      ),
      drawer: Drawer(
        child: MyMenu_Manual()
      ),));
  }
  dispose(){
    subscription.cancel();
  }
}
