# RunningTracker
쏘스 - 소셜 런 클럽(Social Run Club)<br><br>
**1. 개발환경 및 사용언어**  <br> 
------------------------------------------
##### Windows 10 / AndroidStudio / JAVA / Firebase <br><br>
**2. 개발동기**  <br>
------------------------------------------
##### 현대인들은 코로나19 바이러스로 인하여 일상 생활 속에서도 건강의 위협을 받고 헬스장, 체육관과 같은 실내 체육 활동이 제한되어 있는 상황에서 자신의 건강을 관리하기 위하여 걷기, 달리기와 같은 실외 활동을 찾는 사람이 많아졌다. 그래서 우리는 현재와 같은 상황속에서 좀 더 효율적으로 운동을 하고 건강을 관리할 수 있는 어플리케이션을 구상하게 되었다.<br><br>Modern people are walking and running to manage their own health in the situation where indoor sports activities such as gyms are restricted due to the threat of health in daily life due to the coivd-19 virus. Many people were looking for a great outdoor activity. That's why we envisioned an application that would allow us to exercise more efficiently and manage our health.<br><br>
**3. 개발목표와 내용** <br>
-------------------------------------------
##### 사용자들이 애플리케이션을 통하여 자신의 운동 경로를 타인과 공유함으로써 길을 모르거나 새로운 경로를 찾는 사람들에게 그들이 필요로 하는 경로를 찾거나 그 주변의 시설의 정보를 제공하게 된다. 이를 통해, 기존에 불편함을 겪던 사람들은 자신이 필요로 하는 정보를 선택하고 제공받음으로써 보다 효율적인 운동을 할 수 있게 된다.
##### 사용자가 버튼을 클릭함으로써 주행 시작과 끝을 간단하게 설정할 수 있고, 주행이 끝나면 타인과 경로를 공유할지 선택할 수 있다. 또한, 기본적인 운동보조 어플의 역할로 주행자의 속도와 주행거리, 소모한 칼로리를 확인할 수 있으며 누적된 데이터를 통계하여 확인할 수 있다. <br><br>
**4. 기대효과 및 향후 발전 방향** <br>
-------------------------------------------
##### * 웨어러블(SmartWatch 등)과 접합하여 주행 데이터 및 공유받은 경로를 보다 효율적으로 사용/관리할 수 있다.<br>
##### * 자신이 잘 모르거나 처음 가보는 장소에서도 공유된 경로를 분석함으로써 직접 가보지 않아도 운동하기 좋은 장소를 탐색할 수 있다.<br>
##### * 자신과 타인의 경로를 접목시킴으로서, 자신의 동선을 수정해 나가며 가장 효율적인 동선을 갖출 수 있다.<br><br>
**5. 기능 및 서비스**  <br>
-------------------------------------------
##### * 런닝 중 속도, 소모 칼로리, 소요 시간 계산 및 저장 기능 <br>
##### * 어플 사용자들 간의 경로 공유 게시판 운영<br>
##### * 공유된 운동 경로와 현재 경로 비교 <br>
##### * 공유된 운동 경로 이탈 시 진동 및 알림 기능 <br>
##### * 캘린더를 이용한 주기별 나의 운동 통계 기능 <br>
##### * 로그인 유저의 프로필 확인 기능<br><br>
**6. 사용법** <br>
--------------------------------------------
사용자는 소스코드를 Clone시켜 사용하고, 개인 Google API를 발급하여 삽입한다.<br><br>
Google API 키를 발급받고 AndoridManifest.xml에 삽입
```
<meta-data
            android:name="com.google.android.geo.API_KEY"
            android:value="발급받은_Gooogle_API_KEY" />
```
**7. 결과화면** <br>
--------------------------------------------
 Loading<br>
<img src = "https://user-images.githubusercontent.com/77624879/117571540-f4834e00-b109-11eb-936a-691d3182bf37.jpg" width="250px"><br>
 Login & Register & Profile<br>
<img src = "https://user-images.githubusercontent.com/77624879/117571507-cc93ea80-b109-11eb-9c75-12c59532816a.jpg" width="250px">
<img src = "https://user-images.githubusercontent.com/77624879/117571688-9dca4400-b10a-11eb-8896-65ff57b786ae.jpg" width="250px">
<img src = "https://user-images.githubusercontent.com/77624879/117571557-0e249580-b10a-11eb-9af1-c8074d07c20b.jpg" width="250px"><br>
 Main & Board<br>
<img src = "https://user-images.githubusercontent.com/77624879/117571666-80957580-b10a-11eb-97b9-40e51b35dbca.jpg" width="250px">
<img src = "https://user-images.githubusercontent.com/77624879/117571517-dae20680-b109-11eb-98b1-aceac9537238.jpg" width="250px">
<img src = "https://user-images.githubusercontent.com/77624879/117571348-2942d580-b109-11eb-9bff-25e0c02111cc.jpg" width="250px"><br>
 Statistics & Calendar<br>
<img src = "https://user-images.githubusercontent.com/77624879/117568636-5472f800-b0fc-11eb-9c03-acbec31b658d.jpg" width="250px">
<img src = "https://user-images.githubusercontent.com/77624879/117571433-85a5f500-b109-11eb-86fe-19fc3843b2f0.jpg" width="250px"><br>
 WritePost & CheckPost<br>
<img src = "https://user-images.githubusercontent.com/77624879/117571603-3ad8ad00-b10a-11eb-818e-314cf7793375.jpg" width="250px">
<img src = "https://user-images.githubusercontent.com/77624879/117571617-4926c900-b10a-11eb-8ca9-e4f2a373a581.jpg" width="250px"><br>
 SharedRouteInMap<br>
<img src = "https://user-images.githubusercontent.com/77624879/117571646-6a87b500-b10a-11eb-86ef-849afef9e045.jpg" width="250px"><br><br>
**8. 참고자료** <br>
-------------------------------------------------
 1. [https://en.wikipedia.org/wiki/Metabolic_equivalent_of_task] Metabolic equivalent of task, Wikipedia <br>
 2. [https://www.nibiohn.go.jp/eiken/programs/pdf/guidelines2006_ko.pdf] 건강증진을 위한 논문 기준, 2006<br>
 3. [ https://material.io/resources/color/#!/?view.left=0&view.right=0 ] Material Design<br>
 4. [ https://developers.google.com/ ] Google Developer<br>
 

 

