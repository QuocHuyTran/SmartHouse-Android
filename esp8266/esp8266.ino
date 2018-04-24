#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>
#include <WiFiClient.h>
#include <ESP8266WebServer.h>

#define FIREBASE_HOST "smart-77c02.firebaseio.com" //Thay bằng địa chỉ firebase của bạn
#define FIREBASE_AUTH ""   //Không dùng xác thực nên không đổi
#define WIFI_SSID "Redmi"   //Thay wifi và mật khẩu
#define WIFI_PASSWORD "123123123"

ESP8266WebServer server(80);
int rain_sensor=16;
int ledRain=14;
void setMODE(){
  pinMode(rain_sensor,INPUT);
  pinMode(ledRain,OUTPUT);
}

void setup() {

  Serial.begin(9600);

  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);

  Serial.print("connecting");

  while (WiFi.status() != WL_CONNECTED) {

    Serial.print(".");

    delay(500);

  }

  Serial.println();

  Serial.print("connected: ");

  Serial.println(WiFi.localIP());
  Firebase.begin(FIREBASE_HOST,FIREBASE_AUTH);
   setMODE();
}


int count_rain=0;
void loop() {
StaticJsonBuffer<200> jsonBuffer;
bool lv_dt=Firebase.getBool("lv_dt");
bool lv_dh=Firebase.getBool("lv_dh");

bool kc_dt=Firebase.getBool("kc_dt");
bool kc_vn=Firebase.getBool("kc_vn");

bool gd_dt=Firebase.getBool("gd_dt");
bool gd_dp=Firebase.getBool("gd_dp");

bool br_dt=Firebase.getBool("br_dt");

int lvdtPower=Firebase.getInt("lv00");
int lvdhPower=Firebase.getInt("lv01");
int kcdtPower=Firebase.getInt("kc00");
int gddtPower=Firebase.getInt("gd00");
int gddpPower=Firebase.getInt("gd01");
int brdtPower=Firebase.getInt("br00");

if(lv_dt){lvdtPower=lvdtPower+1;}
if(lv_dh){lvdhPower=lvdhPower+1;}
if(kc_dt){kcdtPower=kcdtPower+1;}
if(gd_dt){gddtPower=gddtPower+1;}
if(gd_dp){gddpPower=gddpPower+1;}
if(br_dt){brdtPower=brdtPower+1;}

Firebase.setInt("lv00",lvdtPower);Firebase.setInt("lv01",lvdhPower);
Firebase.setInt("kc00",kcdtPower);
Firebase.setInt("gd00",gddtPower);Firebase.setInt("gd01",gddpPower);
Firebase.setInt("br00",brdtPower);


//Serial.print(ld_dt);
JsonObject& root = jsonBuffer.createObject();
root["lv_dt"] = lv_dt;
root["lv_dh"] = lv_dh;

root["kc_dt"] = kc_dt;
root["kc_vn"] = kc_vn;

root["gd_dt"] = gd_dt;
root["gd_dp"] = gd_dp;

root["br_dt"] = br_dt;


Firebase.setBool("lr",digitalRead(rain_sensor));
bool isRain=digitalRead(rain_sensor);
if(!isRain)
{
  digitalWrite(ledRain,HIGH);
//  Serial.println("mưa");
//  count_rain++;
}
else{
  digitalWrite(ledRain,LOW);
//  Serial.println("0 mưa");
}


root.printTo(Serial);


//delay(2000);
}
