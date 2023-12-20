Tugas Module 20 mengenai : Membuat API automation framework dengan Java
Deskripsi tugas:
Buat Kerangka Otomasi API dan otomatiskan beberapa kasus
Untuk membuat API Automation Framework, langkah-langkah berikut dapat diikuti:
Pilihlah framework testing: JUnit, TestNG, PyTest, etc.
Pilih HTTP client library untuk membuat API request: Apache HTTP Client, OkHttp, Requests, etc.
Tentukan struktur projek: buat paket untuk requests, tests, utilities, etc.
Tes tulis: tulis test cases untuk titik akhir yang berbeda, tambahkan request parameters, memvalidasi kode status response dan response payload.
Tulis test utilities: Buat utility classes untuk menangani tugas-tugas umum seperti membaca data pengujian dari file, menyiapkan lingkungan pengujian, dll.
Tulis sebuah test runner class untuk melaksanakan tes dan menghasilkan laporan pengujian.
Setelah kerangka kerja disiapkan, Anda dapat mulai mengotomatiskan pengujian API.
Tes positif: uji API untuk respons yang benar dengan input yang benar.
Tes negatif: uji API untuk respons yang salah dengan input yang salah, seperti mengirim tipe data yang salah atau kolom wajib diisi tidak ada.
Tes batas: uji API untuk edge cases yang berbeda, seperti nilai maksimum atau minimum untuk parameter masukan.
Kirimkan contoh kode Anda (Anda dapat mengambil tangkapan layar dan mengompilasinya menjadi presentasi Google kecil)

Scenarios in this project ( scenarios are made using REST-API: https://reqres.in/ ):
1. Test GET
   1st. GET positive scenario : When GET request is successful, there will be 200 OK response
   2nd. GET positive scenario : When GET request is successful, there will be 200 OK response. And the test will pass if the validation of json schema is also successful.
   3rd. GET negative scenario : Get a NonExisting User, should return 400 not found response
2. Test POST
   1st. POST positive Scenario : try to create user and check status code 201
   2nd. POST negative Scenario (No password data) : try to register user (registered user must have an email and password) but there is no password in this scenario. Hence, the response should be 400
3. Test PUT : should return 200 response
4. Test PATCH : should return 200 response
5. Test DELETE : should return 204 response
6. Test Edge Case
   Edge case example: 
    Scenario: Log in to a website
    Test Data: Incorrect username or password when logging in
    Expected Result: The user should not be able to log in, and an error message should be displayed
      1st. Test NORMAL login scenario : correct email and correct password
      2nd. Test INCORRECT EMAIL login scenario (negative scenario) : incorrect email and correct password
      3rd. Test INCORRECT password login scenario (negative scenario) : correct email and incorrect password




