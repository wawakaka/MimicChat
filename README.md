# MimicChat

Proses pembuatan aplikasi ini

- menentukan kebutuhan aplikasi
  - menentukan library yang akan digunakan
  - menentukan alur data
- membuat layout dasar
  - membuat `ListView` dan custom adapternya
- membuat class untuk menghandel adapter beserta datanya
- membuat class untuk menghandel pengambilan data
- mencoba mengambil data, memparsingnya dan memasukannya ke adapter
- mengubah data ke bentuk yang diinginkan seperti data datetime ke time
  - menghandel pesan yang mengandung url
  - mengambil url dari pesan
  - mengambil informasi website dari url tersebut
  - memasukan data yang sudah diubah tadi ke adapter
- merubah tampilan sesuai yang diinginkan

Secara garis besar prosesnya adalah
- buat layout
- buat adapter
- ambil data dan cari `error`
- masukan data keadapter
- tampilkan dan cari `error`
- udah tampilan dan cari `error`

# Library yang digunakan

- `okhttp` untuk mengambil data json dari internet
- `jsoup` untuk mengambil metadata website dari url yang terdapat pada pesan
