<h1 align="center">Sistem Aplikasi Presensi</h1>

## Overview

Aplikasi ini bla bla bla ya gitulah project semester 2

## Explaining some "complicated" things

<br/>
<h2> - Automatic presence</h2>

```sql
select dj.id, dj.hari as "Date", dj.jam as "Jam Mulai", ADDTIME(dj.jam, dj.durasi) as "Jam Selesai", m.nama as "Nama Mapel" from tb_detail_jadwal as dj join tb_jadwal as j on dj.id_jadwal = j.id join tb_karyawan as k on dj.id_karyawan = k.id join tb_mapel as m on j.id_mapel = m.id where k.id = 6 and (current_time > dj.jam && current_time < ADDTIME(dj.jam, dj.durasi) && dj.hari = WEEKDAY(CURRENT_DATE) && dj.id not in (select p.id_detail_jadwal from tb_presensi as p join tb_detail_jadwal as dj on p.id_detail_jadwal = dj.id where weekday(p.tanggal) = weekday(current_date) and (current_time > dj.jam && current_time < ADDTIME(dj.jam, dj.durasi)))) order by ADDTIME(dj.jam, dj.durasi) asc limit 1;
```

E: Shows employee's schedule by filtering using where clause reference to employee's id and then filter it again in date, if the current time matches employee's schedule time and not already submitted in presence table (tb_presensi)

<h2> - JodaTime</h2>

```java
DateTimeFormatter fmt = DateTimeFormat.forPattern("hh:mm aa");
DateTimeFormatter myFormatObj = DateTimeFormat.forPattern("HH:mm:ss");
LocalTime formattedTime = fmt.parseLocalTime(lTime);
String time = myFormatObj.print(formattedTime);

displayText.setText(time);
```

E: Pick two formatter, one is to format the am and pm time and the other one is to format the formatted time before. and after that declare string variable for the localtime, then set the display text for "datefield" field.
