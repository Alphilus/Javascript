let products = [
    {
        name: "Iphone 13 Pro Max", // Tên sản phẩm
        price: 3000000, // Giá mỗi sản phẩm
        brand: "Apple", // Thương hiệu
        count: 2, // Số lượng sản phẩm trong giỏ hàng
    },
    {
        name: "Samsung Galaxy Z Fold3",
        price: 41000000,
        brand: "Samsung",
        count: 1,
    },
    {
        name: "IPhone 11",
        price: 15500000,
        brand: "Apple",
        count: 1,
    },
    {
        name: "OPPO Find X3 Pro",
        price: 19500000,
        brand: "OPPO",
        count: 3,
    },
]

// 1. In ra thông tin các sản phẩm trong giỏ hàng theo cấu trúc sau
// Tên - Giá - Thương Hiệu - Số lượng
// Ví dụ : OPPO Find X3 Pro - 19500000 - OPPO - 3
for(let i = 0; i < products.length; i++){
    console.log(`${products[i].name} - ${products[i].price} - ${products[i].brand} - ${products[i].count}`)
}

// 2. Tính tổng tiền tất cả sản phẩm trong giỏ hàng
// Tổng tiền mỗi sản phẩm = price * count

let totalPrice = 0
for(let i = 0; i < products.length; i++){
    totalPrice += products[i].price * products[i].count;
}
console.log(totalPrice);

// 3. Tìm các sản phẩm của thuơng hiệu "Apple"
let appleProducts = []
for(let i = 0; i < products.length; i++){
    if(products[i].brand == "Apple"){
        appleProducts.push(products[i]);
    }
}
console.log(appleProducts);

// 4. Tìm các sản phẩm có giá > 20000000
let findPrice = [];
for(let i = 0; i < products.length; i++){
    if(products[i].price > 20000000){
        findPrice.push(products[i]);
    }
}
console.log(findPrice);

// 5. Tìm các sản phẩm có chữ "pro" trong tên (Không phân biệt hoa thường)

let findPro = [];
for(let i = 0; i < products.length; i++){
    if(products[i].name.toLowerCase().includes("pro")){
        findPro.push(products[i]);
    }
}
console.log(findPro);