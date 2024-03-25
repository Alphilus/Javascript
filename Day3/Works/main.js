// Câu 1. Tạo 1 thẻ p có id=“text”, có nội dung bất kỳ (có thể tạo bằng HTML hay Javascript - tùy chọn). Yêu cầu
    // Đặt màu văn bản thành #777
    // Đặt kích thước phông chữ thành 2rem
    // Đặt nội dung HTML thành Tôi có thể làm <em> bất cứ điều gì </em> tôi muốn với JavaScript.
// Câu 2. Sử dụng vòng lặp để đặt màu chữ cho tất cả thẻ li thành màu blue (tạo thẻ ul-li bằng html)'
const li = document.getElementsByTagName("li");

for (var i = 0; i < li.length; i++) {
  li[i].style.color = 'blue';
}
// Câu 3. Cho mã HTML có nội dung như sau (tạo thẻ ul-li bằng html):
const list = document.getElementById("list");

for (var i = 8; i <= 10; i++){
    var newItems = document.createElement("li");
    newItems.textContent = "Item" + i;
    list.appendChild(newItems);
}

list.children[0].style.color = "red";
list.children[2].style.backgroundColor = "blue";

list.removeChild(list.children[3]);

var newItems = document.createElement("li");
newItems.textContent = "Item Mới";
list.insertBefore(newItems, list.children[3]);