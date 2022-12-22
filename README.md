# Diary App (Team TMI)

팀원

- [EnoughKK](https://github.com/EnoughKK) - BottomNavigation, Calendar페이지, Flush페이지
- [Sir-u](https://github.com/Sir-u) - Memo페이지, PhoneBook페이지

Github: [https://github.com/EnoughKK/Android_Mobile_Diary_App.git](https://github.com/EnoughKK/Android_Mobile_Diary_App.git)

Figma: https://www.figma.com/file/PVVSbSKDYUZ4vMX5CcxTjk/TMI-Project?node-id=0%3A1&t=hmAMXDSnQHo3Akww-1

------

### `📌 목차`

1. [구현 환경](https://www.notion.so/Diary-App-Team-TMI-c40c4882bda94bc099207c3174c6177c)
2. [기능 설명](https://www.notion.so/Diary-App-Team-TMI-c40c4882bda94bc099207c3174c6177c)
3. [데이터베이스 설계](https://www.notion.so/Diary-App-Team-TMI-c40c4882bda94bc099207c3174c6177c)
4. [테스트 방법](https://www.notion.so/Diary-App-Team-TMI-c40c4882bda94bc099207c3174c6177c)

---
## `1. 구현 환경`

**프레임워크**

⇒ Android Studio

**사용언어**

⇒ Java

**형상관리**

⇒ [Git/Github](https://github.com/Sir-u/Android_Mobile_Diary_App)

**협업**

⇒ [Figma](https://www.figma.com/file/PVVSbSKDYUZ4vMX5CcxTjk/TMI-Project?node-id=0%3A1&t=hmAMXDSnQHo3Akww-1)

---

## `2. 기능 설명`

### **<페이지 이동>**

- bottomNavigaion의 아이템을 클릭하여 페이지 이동

- 관련 파일
    
    **java 파일**
    
    - MainActivity
    
    **xml 파일**
    
    - activity_main.xml
    - res > menu > bottom_navigation_menu.xml
    

### **<일정 관리> - Calendar페이지**

- **일정 조회** → 달력에 날짜를 클릭하면 해당 날짜의 일정을 조회할 수 있습니다.
    
    (디폴트는 오늘 날짜입니다.)
    
- **일정 검색** → 검색창에 일정제목을 입력하여 검색버튼을 누르면 해당 일정제목의 일정을 검색할 수 있습니다.
    
    (단, 선택한 날짜내에서만 검색가능)
    
- **일정 추가** → 오른쪽 하단의 플러스 버튼을 클릭하여 새로운 일정을 추가할 수 있습니다.
    
    (달력에서 선택한 날짜에 일정이 추가됩니다.) 
    
    ** 일정 타이틀과 내용을 입력 후 왼쪽 상단의 뒤로가기버튼을 클릭시 자동으로 달력에서 선택한 날짜에 일정이 추가됩니다.
    
- **일정 수정 →** 수정하고 싶은 일정을 클릭한후 타이틀과 내용을 수정후 뒤로가기 버튼을 클릭하면 수정이 되어 업데이트됩니다.
- **일정 삭제** →  삭제하고 싶은 일정을 길게 누르면 dialog창으로 삭제하기가 뜹니다.
    
    dialog창의 삭제하기 버튼을 누르면 최종적으로 일정이 삭제됩니다.
    
- 관련 파일
    <details>
    <summary><b> java 파일 </b></summary>
    <div markdown="1">
    
    - CalendarFragment.java
    - CalendarRVAdapter.java
    - CalendarDetailFragment.java
    - CalendarDatabaseHelper.java
    - DTO > CalendarItemDTO.java

    </div>
    </details>

    <details>
    <summary><b> xml 파일 </b></summary>
    <div markdown="1">

    - fragment_calendar.xml
    - fragment_calendar_detail.xml
    - res > drawable > resource.xml

    </div>
    </details>
    
    <br>

### **<연락처 관리> - PhoneBook페이지**

- **연락처 조회 →** 전화번호부 화면에서 전화번호부 데이터베이스에 저장된 연락처 목록을 조회할 수 있습니다.
    - 연락처는 이름의 가나다 순으로 정렬됩니다.
- **연락처 추가 →** 우측 상단에 ‘+’ 버튼을 누르면 연락처를 추가하는 화면으로 이동하여 연락처를 추가할 수 있습니다.
    - 이름과 전화번호를 입력하여 확인 버튼을 누르면 데이터베이스에 저장됩니다.
    - 전화번호부 화면에서 추가한 연락처를 조회할 수 있습니다.
- **연락처 검색 →** 우측 상단에서 ‘돋보기’ 버튼을 누르면 연락처를 검색할 수 있습니다
    - 연락처는 이름으로 검색할 수 있습니다.
- **연락처 발신 →** 연락처를 클릭하면 전화를 걸 수 있는 화면으로 이동합니다.
- **연락처 수정 및 삭제 →** 연락처를 길게 클릭하면 수정 또는 삭제를 할 수 있는 dialog창이 뜹니다.
    - 수정 버튼을 누를 경우, 이름과 전화번호를 다시 작성하여 연락처 내용을 수정할 수 있습니다.
    - 삭제 버튼을 누를 경우, 연락처가 삭제됩니다.

    <br>
- 관련 파일
    <details>
    <summary><b> java 파일 </b></summary>
    <div markdown="1">
    - PhoneBookFragment.java
    - PhoneBookRVAdapter.java
    - PhoneBookDetailFragment.java
    - PhoneBookDBActivity.java
    - DTO > PhoneBookItemDTO.java

    </div>
    </details>

    <details>
    <summary><b> xml 파일 </b></summary>
    <div markdown="1">

    - fragment_phonebook.xml
    - fragment_phonebook_detail.xml
    - rv_phonebook.xml

    </div>
    </details>
    
    <br>


### **<메모> - Memo페이지**

- **만든 목적**
    - 일정 외의 추가 메모 사항을 기입하기 위해서 만들었습니다.
- **메모 조회 →** 메모 화면에서 메모 데이터베이스에 저장된 메모 목록을 조회할 수 있습니다.
    - 메모는 가장 최근에 작성된 메모 순으로 정렬됩니다.
- **메모 추가 →** 우측 상단의 ‘+’버튼을 누르면 메모를 작성하는 화면으로 이동하여 메모를 추가할 수 있습니다.
    - 제목과 내용을 입력하고 ‘SAVE’ 버튼을 누르면 데이터베이스에 저장됩니다.
    - ‘CANCLE’ 버튼을 누르면 메모 추가가 취소되고 메모 화면으로 이동합니다.
- **메모 내용 확인 및 수정 →** 메모를 클릭하면 메모의 내용을 확인할 수 있습니다.
    - 혹시나 수정하고 싶은 사항이 있으면 내용을 수정한 후 ‘SAVE’ 버튼을 누르면 내용이 수정됩니다.
- **메모 삭제 →** 메모를 길게 클릭하면 삭제를 할 수 있는 dialog 창이 뜹니다.
    - 삭제 버튼을 누를 경우, 메모가 삭제됩니다.

- 관련 파일
    <details>
    <summary><b> java 파일 </b></summary>
    <div markdown="1">
    - MemoFragment.java
    - MemoRVAdapter.java
    - MemoDetailActivity.java
    - MemoDetailFragment.java
    - MemoDBActivity.java
    - DTO > MemoItemDTO.java

    </div>
    </details>

    <details>
    <summary><b> xml 파일 </b></summary>
    <div markdown="1">
    - fragment_memo.xml
    - fragment_memo_detail.xml
    - rv_memo.xml
    </div>
    </details>
    
    <br>

### **<Flush> - Flush페이지**

- 만든 목적
    
    ⇒ 잊고 싶은 기억이나 짜증나는 일이 있을 때 그 일을 없었던 일로 날려버리면서 스트래스를 해소하고자 하는 취지로 만들었습니다.
    
- 내용을 메모칸에 적은 후 플러쉬 버튼을 누르면 내용이 서서히 사라집니다.

- 관련 파일
    <details>
    <summary><b> java 파일 </b></summary>
    <div markdown="1">
    - FlushFragment.java

    </div>
    </details>

    <details>
    <summary><b> xml 파일 </b></summary>
    <div markdown="1">
    - fragment_flush.xml
    - res > anim> alpha.xml
    </div>
    </details>
    
    <br>


---

## `3. 데이터베이스 설계`

### **<일정 관리> - Calendar페이지 >** CalendarDatabaseHelper

- “CalendarList” 테이블
    
    ⇒ id 값을 자동으로 생성하고 일정 title, content, data 값을 받아 저장하는 테이블
    
- 메서드
    - `getCalendarItems(String selectedDate)`
        
        ⇒ 달력에서 날짜를 선택했을 때 해당 날짜의 일정을 조회하기 위한 함수입니다.
        
        ⇒ 달력에서 선택한 날짜를 인자로 받습니다.
        
    - `searchCalendarItems(String searchTitle, String selectedDate)`
        
        ⇒ 일정을 검색했을 때 해당 타이틀의 일정을 조회하기 위한 함수입니다.
        
        ⇒ 검색한 일정의 타이틀과 달력에서 선택한 날짜를 인자로 받습니다.
        
    - `InsertCalendar(String _title, String _content, String _date)`
        
        ⇒ 일정을 새로 생성할 때 “CalendarList” 테이블에 일정을 추가하기 위한 함수입니다.
        
        ⇒ 일정 타이틀, 내용, 날짜를 인자로 받습니다.
        
    - `UpdateCalendar(String _title, String _content, String _date, int _id)`
        
        ⇒ 일정을 수정할 때 “CalendarList” 테이블에 해당 일정을 수정하기 위한 함수입니다.
        
        ⇒ 해당 일정의 타이틀, 내용, 날짜, 아이디값를 인자로 받습니다.
        
    - `DeleteCalendar(int _id)`
        
        ⇒ 선택한 일정을 삭제하기 위한 함수입니다.
        
        ⇒ 선택한 일정의 아이디를 인자로 받습니다.
        
    <br>

### <전화번호부> - 전화번호부 페이지 > PhoneBookDBActivity

“PhoneBook.db” 데이터베이스 생성
“PhoneBookList” 테이블 생성
⇒ id 값을 자동으로 생성하고 이름, 전화번호 값을 받아 저장하는 테이블입니다.

- 메서드

    - `getPhoneBookItem()`
        ⇒ 전화번호부 화면 전환 시, 저장된 데이터들을 조회하기 위한 메서드입니다.

    - `SearchPhoneBook(String _name)`
        ⇒ 이름을 검색 했을 때, 해당 이름의 연락처를 조회하기 위한 메서드입니다.
        ⇒ 검색하고자 하는 이름을 인자로 받습니다.

    - `InsertPhoneBook(String _name, String _number)`
        ⇒ 연락처를 새로 추가할 때, “PhoneBookList” 테이블에 연락처를 추가하기 위한 메서드입니다.
        ⇒ 연락처 이름, 전화번호를 인자로 받습니다.

    - `UpdatePhoneBook(String _name, String _number, int _id)`
        ⇒ 연락처를 수정할 때, “PhoneBookList” 테이블에 해당 연락처를 수정하기 위한 메서드입니다.
        ⇒ 연락처 이름, 전화번호, id 값을 인자로 받습니다.

    - `DeletePhoneBook(int _id)`
        ⇒ 선택한 연락처를 삭제하기 위한 메서드입니다.
        ⇒ 선택한 연락처의 id 값을 인자로 받습니다.

    <br>

### <메모> - 메모 페이지 > MemoDBActivity

“Memo.db” 데이터베이스 생성
“MemoList” 테이블 생성
⇒ id 값을 자동으로 생성하고 제목, 내용 값을 받아 저장하는 테이블입니다.

- 메서드

    - `GetMemoItem()`
        ⇒ 메모 화면 전환 시, 저장된 데이터들을 조회하기 위한 메서드입니다.

    - `InsertMemo(String _title, String _content)`
        ⇒ 메모를 새로 추가할 때, “MemoList” 테이블에 메모를 추가하기 위한 메서드입니다.
        ⇒ 메모 제목, 내용를 인자로 받습니다.

    - `UpdateMemo(String _title, String _content, int _id)`
        ⇒ 메모를 수정할 때, “MemoList” 테이블에 해당 메모를 수정하기 위한 메서드입니다.
        ⇒ 메모 제목, 내용, id 값을 인자로 받습니다.

    - `DeleteMemo(int _id)`
        ⇒ 선택한 메모를 삭제하기 위한 메서드입니다.
        ⇒ 선택한 메모의 id 값을 인자로 받습니다.
    
    <br>
----
## 4. 테스트 방법

### **<일정 관리> - Calendar페이지**

1. **일정 입력**
    
    ⇒ 원하는 날짜를 달력에서 선택한 후 오른쪽 하단의 플러스버튼을 누르면 일정을 입력할 수 있는 화면으로 넘어갑니다.
    
    ⇒ 일정 타이틀과 내용을 입력 후 뒤로가기 버튼을 누르면 입력이 완료됩니다.
    
    ** 같은 날짜에 3개, 다른 날짜에 1개정도 입력해 주세요.
    

1. **일정 조회**
    
    ⇒ 일정을 입력한 날짜를 클릭하면 해당 날짜의 일정이 검색창 하단에 표시됩니다.
    
    ⇒ 표시된 일정을 클릭하면 상세 내용을 확인할 수 있습니다.
    
2. **일정 검색**
    
    ⇒ 검색하고자 하는 일정의 날짜를 클릭하신 후 검색창에 입력한 일정의 타이틀을 입력한후 오른쪽의 검색 버튼을 누르면 하단에 해당 일정이 표시됩니다.
    
    ** 선택된 날짜의 일정만 검색됩니다.
    

1. **일정 수정**
    
    ⇒ 수정하고자 하는 일정을 클릭하고 타이틀이나 내용을 변경 후 뒤로가기를 클릭하면 수정이 완료됩니다.
    
2. **일정 삭제**
    
    ⇒ 삭제하고자 하는 일정을 길게 누르면 “삭제하기” 다이얼로그가 뜹니다.
    
    ⇒ 다이얼로그의 “삭제하기”버튼을 클릭하면 삭제가 완료됩니다.
    

<br>

### **<연락처 관리> - PhoneBook페이지**

1. **연락처 조회**
⇒ 전화번호부 화면에서 저장되어 있는 연락처를 확인할 수 있습니다.
**연락처는 이름 순으로 정렬됩니다.

1. **연락처 입력**
⇒ 우측 상단 ‘+’ 버튼을 누르면 연락처를 추가하는 화면으로 이동합니다.
⇒ 연락처를 입력하고 확인 버튼을 누르면 연락처가 추가됩니다.

1. **연락처 검색**
⇒ 상단 검색창에 이름을 입력 후 우측 상단 ‘돋보기’ 버튼을 누르면 원하는 연락처의 이름을 검색할 수 있습니다.

1. **연락처 수정 및 삭제**
⇒ 연락처를 길게 누르면 수정 및 삭제 Dialog 화면이 뜹니다.
⇒ ‘수정하기’ 버튼을 누르면 연락처를 수정할 수 있는 창이 뜨며, 여기서 연락처를 다시 입력하여 수정할 수 있습니다.
⇒ ‘삭제하기’ 버튼을 누르면 연락처를 삭제할 수 있습니다.

<br>

### <메모> - Memo페이지

1. **메모 조회**
⇒ 메모 화면에서 저장되어 있는 메모를 확인할 수 있습니다.
메모는 작성된 순서로 정렬됩니다.

1. **메모 입력**
⇒ 우측 상단 ‘+’ 버튼을 누르면 메모를 추가하는 화면으로 이동합니다.
⇒ 메모의 제목과 내용을 입력하고 나서 ‘SAVE’ 버튼을 누르면 메모가 저장됩니다.
⇒ 메모 작성을 취소하고 싶다면 ‘CANCLE’ 버튼을 눌러 메모 작성을 취소합니다.

1. **메모 수정**
⇒ 수정하고자 하는 메모를 클릭하면 메모 내용을 수정할 수 있습니다.
⇒ 수정을 하고 나서 ‘SAVE’ 버튼을 누르면 수정된 메모가 저장됩니다.
⇒ 메모 수정을 취소하고 싶다면 ‘CANCLE’ 버튼을 눌러 메모 수정을 취소합니다.

1. **메모 삭제**
⇒ 삭제하고자 하는 메모를 길게 누르면 삭제하기 dialog 창이 뜹니다.
⇒ ‘삭제하기’ 버튼을 눌러 메모를 삭제할 수 있습니다.

<br>

### < Flush > - Flush페이지

1. 메모장에 Flush하고싶은 내용을 입력합니다.
2. Flush 버튼을 누르면 내용이 서서히 사라지는 것을 확인합니다.
