// Truy cập vào thẻ h1 có id=“heading” thay đổi màu chữ thành ‘red’, và in hoa nội dung của thẻ đó
let headingElement = document.getElementById('heading');
headingElement.style.color = 'red';
headingElement.textContent = headingElement.textContent.toUpperCase();
// Thay đổi màu chữ của tất cả thẻ có class “para” thành màu “blue” và có font-size = “20px”
let className = document.getElementsByClassName('para');
for (let i = 0; i < className.length; i++){
    className[i].style.color = 'blue';
    className[i].style.fontSize = '20px';
}
// Thêm thẻ a link đến trang ‘facebook’ ở đằng sau thẻ có class “para-3”
const a = document.createElement("a");
a.innerText="facebook";
a.href="https://facebook.com";
const div = document.querySelector("div");
document.body.insertBefore(a,div);
// Thay đổi nội dung của thẻ có id=“title” thành “Đây là thẻ tiêu đề”
let changeId = document.getElementById("title");
changeId.textContent = "Đây là thẻ tiêu đề";
// Thêm class “text-bold” vào thẻ có class=“description” (định nghĩa class “text-bold” có tác dụng in đậm chữ)
let elements = document.getElementsByClassName("description");
for(let i = 0; i < elements.length; i++){
    elements[i].classList.add("text-bold");
}
// Thay thế thẻ có class=“para-3” thành thẻ button có nội dung là “Click me”

// Copy thẻ có class=“para-2” và hiển thị ngay đằng sau thẻ đó

// Xóa thẻ có class=“para-1”
