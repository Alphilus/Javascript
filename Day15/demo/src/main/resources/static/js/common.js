// Xử lý active menu
function activeMenu() {
    // Lây đường dẫn hiện tại
    let path = window.location.pathname;
    console.log(path)

    // Xử lý active menu
    const menuItems = document.querySelectorAll('#main-menu .navbar-nav .nav-link')
    menuItems.forEach(function (menu) {
        if (menu.getAttribute('href') === path) {
            menu.classList.add('active')
        }
    })
}

activeMenu()

toastr.options = {
    "closeButton": true,
    "debug": false,
    "newestOnTop": false,
    "progressBar": true,
    "positionClass": "toast-top-center",
    "preventDuplicates": true,
    "onclick": null,
    "showDuration": "300",
    "hideDuration": "1000",
    "timeOut": "5000",
    "extendedTimeOut": "1000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
}