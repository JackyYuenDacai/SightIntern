import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/foundation.dart' show SynchronousFuture;
import 'package:flutter_localizations/flutter_localizations.dart';
import './main.dart';

class I8N{
  I8N(this.locale);
  final Locale locale;
  static I8N of(BuildContext context){
    return Localizations.of<I8N>(context, I8N);
  }
  static Map<String,Map<String,String>> I8N_values = {
    'en':{
      'apptitle':'CaritasApp',
      'title':'Lavatory Management System',
      'students_title':'Students',
      'home':'Home',
      'rfid_title':'RFID Page',
      'manuel_title':'Manuel Page',
      'setting_title':'Setting Page',
      'confirm':'Confirm',
      'cancel':'Cancel',
      'add_student':'Add Student',
      'add_staff':'Add Staff',
      'location_text':'Location ',
      'submit_text':'Submit',
      'login':'Sign in',
      'username':'Username',
      'password':'Password',
      'student_name':'Student\'s Name',
      'student_hint':'Please enter student\'s name',
      'staff_name':'Staff\'s Name',
      'staff_hint':'Please enter staff\'s name',
      'in_time':'Entry Time',
      'out_time':'Exit time',

      'check':'Check',
      'mistake':'Mistake',
      'toilet':'Toilet',
      'poo_a':'Poo',
      'consistency':'Consistency',
      'na':'N/A',
      'clean':'Clean',
      'dirty':'Dirty',
      'nothing':'Nothing',
      'wee':'Wee',
      'poo':'Poo',
      'both':'both',
      'few':'Few',
      'normal':'Normal',
      'much':'Much',
      'soft':'Soft',
      'hard':'Hard',
      'rot':'Rot',
      'dilute':'Dilute',
      'color':'Color',
      'yellow':'Yellow',
      'brown':'Brown',
      'black':'Black',
      'blood':'Blood',
      'goo':'Goo',
    },
    'zh':{
      'apptitle':'明愛樂勤應用程式',
      'title':'如廁管理系統',
      'students_title':'學生',
      'home':'主頁',
      'rfid_title':'無線感應頁面',
      'manuel_title':'手動加入',
      'setting_title':'設定',
      'confirm':'確定',
      'cancel':'取消',
      'add_student':'添加學生',
      'add_staff':'添加職員',
      'location_text':'地點 ',
      'submit_text':'提交',
      'login':'登入',
      'username':'帳戶名稱',
      'password':'密碼',
      'student_name':'學生姓名',
      'student_hint':'請輸入學生姓名',
      'staff_name':'職員姓名',
      'staff_hint':'請輸入職員姓名',
      'in_time':'進入時間',
      'out_time':'離開時間',

      'check':'驗片',
      'mistake':'遺便',
      'toilet':'如廁',
      'poo_a':'大便量',
      'consistency':'大便質',
      'na':'不適用',
      'clean':'淨片',
      'dirty':'污片',
      'nothing':'無排出',
      'wee':'小便',
      'poo':'大便',
      'both':'大、小便',
      'few':'少',
      'normal':'中',
      'much':'多',
      'soft':'軟',
      'hard':'硬',
      'rot':'爛',
      'dilute':'稀',
      'color':'大便色',
      'yellow':'黃',
      'brown':'棕',
      'black':'黑',
      'blood':'含血',
      'goo':'含潺',
    },
  };
  String get apptitle{return I8N_values[locale.languageCode]['apptitle'];}
  String get title{return I8N_values[locale.languageCode]['title'];}
  String get home{return I8N_values[locale.languageCode]['home'];}
  String get students_title{return I8N_values[locale.languageCode]['students_title'];}
  String get manuel_title{return I8N_values[locale.languageCode]['manuel_title'];}
  String get rfid_title{return I8N_values[locale.languageCode]['rfid_title'];}
  String get setting_title{return I8N_values[locale.languageCode]['setting_title'];}
  String get confirm{return I8N_values[locale.languageCode]['confirm'];}
  String get cancel{return I8N_values[locale.languageCode]['cancel'];}
  String get add_student{return I8N_values[locale.languageCode]['add_student'];}
  String get add_staff{return I8N_values[locale.languageCode]['add_staff'];}

  String get location_text{return I8N_values[locale.languageCode]['location_text'];}
  String get submit_text{return I8N_values[locale.languageCode]['submit_text'];}
  String get login{return I8N_values[locale.languageCode]['login'];}

  String get username{return I8N_values[locale.languageCode]['username'];}
  String get password{return I8N_values[locale.languageCode]['password'];}
  String get student_name{return I8N_values[locale.languageCode]['student_name'];}
  String get student_hint{return I8N_values[locale.languageCode]['student_hint'];}
  String get staff_name{return I8N_values[locale.languageCode]['staff_name'];}
  String get staff_hint{return I8N_values[locale.languageCode]['staff_hint'];}
  String get in_time{return I8N_values[locale.languageCode]['in_time'];}
  String get out_time{return I8N_values[locale.languageCode]['out_time'];}

  String get check{return I8N_values[locale.languageCode]['check'];}
  String get mistake{return I8N_values[locale.languageCode]['mistake'];}
  String get toilet{return I8N_values[locale.languageCode]['toilet'];}
  String get poo_a{return I8N_values[locale.languageCode]['poo_a'];}
  String get consistency{return I8N_values[locale.languageCode]['consistency'];}
  String get na{return I8N_values[locale.languageCode]['na'];}
  String get clean{return I8N_values[locale.languageCode]['clean'];}
  String get dirty{return I8N_values[locale.languageCode]['dirty'];}
  String get nothing{return I8N_values[locale.languageCode]['nothing'];}
  String get wee{return I8N_values[locale.languageCode]['wee'];}
  String get poo{return I8N_values[locale.languageCode]['poo'];}
  String get both{return I8N_values[locale.languageCode]['both'];}
  String get few{return I8N_values[locale.languageCode]['few'];}
  String get normal{return I8N_values[locale.languageCode]['normal'];}
  String get much{return I8N_values[locale.languageCode]['much'];}
  String get soft{return I8N_values[locale.languageCode]['soft'];}
  String get hard{return I8N_values[locale.languageCode]['hard'];}
  String get rot{return I8N_values[locale.languageCode]['rot'];}
  String get dilute{return I8N_values[locale.languageCode]['dilute'];}
  String get color{return I8N_values[locale.languageCode]['color'];}
  String get yellow{return I8N_values[locale.languageCode]['yellow'];}
  String get brown{return I8N_values[locale.languageCode]['brown'];}
  String get black{return I8N_values[locale.languageCode]['black'];}
  String get blood{return I8N_values[locale.languageCode]['blood'];}
  String get goo{return I8N_values[locale.languageCode]['goo'];}

}
class I8NDelegate extends LocalizationsDelegate<I8N>{
  const I8NDelegate();
  @override
  bool isSupported(Locale locale) => ['en', 'zh'].contains(locale.languageCode);

  @override
  Future<I8N> load(Locale locale) {
    // Returning a SynchronousFuture here because an async "load" operation
    // isn't needed to produce an instance of DemoLocalizations.
    return SynchronousFuture<I8N>(I8N(locale));
  }

  @override
  bool shouldReload(I8NDelegate old) => false;
}
