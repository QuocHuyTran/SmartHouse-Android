#include<SoftwareSerial.h>
#include<ArduinoJson.h>
SoftwareSerial mySerial(0,1);
int ledLV=13;
int dieuhoaLV=12;
int ledKC=11;
int voinuoc=10;
int rightled=9;
int leftled=8;
int bedroom=7;

int SoundSensor=A0;

void setup() {
  // put your setup code here, to run once:
  mySerial.begin(9600);
  Serial.begin(9600);
  setIO();
}

void setIO(){
  pinMode(ledLV,OUTPUT);
  pinMode(dieuhoaLV,OUTPUT);
  pinMode(ledKC,OUTPUT);
  pinMode(voinuoc,OUTPUT);
  pinMode(rightled,OUTPUT);
  pinMode(leftled,OUTPUT);
  pinMode(bedroom,OUTPUT);
  pinMode(SoundSensor,INPUT);
}

void loop() {
//  Serial.println("hello");
//  String json="{\"lv_dt\":1,\"lv_dh\":0,\"kc_dt\":1,\"kc_vn\":0,\"gd_dt\":1,\"gd_dp\":0,\"br_dt\":1}";
//    StaticJsonBuffer<200> jsonBuffer;
//    JsonObject& root = jsonBuffer.parseObject(json);
//
//    digitalWrite(ledLV,root["lv_dt"]);
//    digitalWrite(dieuhoaLV,root["lv_dh"]);
//    
//    digitalWrite(ledKC,root["kc_dt"]);
//    digitalWrite(voinuoc,root["kc_vn"]);
//    
//    digitalWrite(rightled,root["gd_dp"]);
//    digitalWrite(leftled,root["gd_dt"]);
//
//    digitalWrite(bedroom,root["br_dt"]);

//    Serial.println(analogRead(A0));
    
//    Serial.println(value);
  // put your main code here, to run repeatedly:
  if(mySerial.available()){
    String msg=mySerial.readString();
    Serial.print("dữ liệu nhận:");
    Serial.println(msg);
    StaticJsonBuffer<200> jsonBuffer;
    JsonObject& root = jsonBuffer.parseObject(msg);

    digitalWrite(ledLV,root["lv_dt"]);
    digitalWrite(dieuhoaLV,root["lv_dh"]);
    
    digitalWrite(ledKC,root["kc_dt"]);
    digitalWrite(voinuoc,root["kc_vn"]);
    
    digitalWrite(rightled,root["gd_dp"]);
    digitalWrite(leftled,root["gd_dt"]);

    digitalWrite(bedroom,root["br_dt"]);
//    JsonObject& root = jsonBuffer.parseObject(json);
//    int value=root["lv_dt"];
//    Serial.println(value);
  }
//  delay(2000);
}
