let todo = [
    {
        id: 1,
        title: "Đi chơi",
        status: true
    },
    {
        id: 2,
        title: "Làm bài tập",
        status: false
    },
    {
        id: 3,
        title: "Đá bóng",
        status: true
    }
];

const generateId = () => {
    if (todo.length === 0) {
        return 1;
    }
    return Math.max(...todo.map(todo => todo.id)) +1;
}

const ulEL = document.querySelector("ul");
const rederTodos = (todo) => {
    ulEL.innerHTML = "";
    if (todo.length === 0){
        ulEL.insertAdjacentHTML("afterbegin", "<li>Danh sách công việc trống</li>");
        return;
    }

    let html = "";
    todo.forEach(todo => {
        html += `
            <li>
                <input 
                    type="checkbox" ${todo.status ? 'checked': ''}
                    onchange="toggleStatus(${todo.id})
                "/>
                <span class=${todo.status ? "active" : ""}> ${todo.title} </span>
                <button onclick="editTodo(${todo.id})">Edit</button>
                <button onclick="deleteTodo(${todo.id})">Delete</button>
            </li>
        `;
        // if(todo.status){
        //     html += `
        //     <li>
        //     <input type="checkbox" checked/><span> ${todo.title} </span>
        //     <button>Edit</button>
        //     <button>Delete</button>
        //     </li>
        //     `
        // } else {
        //     html += `
        //     <li>
        //     <input type="checkbox"/><span> ${todo.title} </span>
        //     <button>Edit</button>
        //     <button>Delete</button>
        //     </li>
        //     `
        // }
    })
    ulEL.innerHTML = html;
}

//Thêm công việc
const inputTodo = document.getElementById("input-todo");
const btnAdd = document.getElementById("btn-add");

btnAdd.addEventListener("click", () => {
    //Lấy nd trong ô input
    const title = inputTodo.value.trim();

    //Kiểm tra nd có rỗng không
    if (title === "") {
        alert("Vui lòng nhập tiêu đề");
        return;
    }

    //Tạo todo mới
    const newTodo = {
        id: generateId(),
        title,
        status: false
    }

    //Thêm todo vào danh sách
    todo.push(newTodo);
    rederTodos(todo);

    inputTodo.value = "";
    inputTodo.focus();
})

const deleteTodo = (id) => {
    const confirm = window.confirm("Are you sure you want to delete?");
    if (confirm) {
        todo = todo.filter(todo => todo.id!== id);
        rederTodos(todo);
    }
}

const editTodo = (id) => {
    const edit = window.prompt("Are you sure you want to edit")
    if (edit) {
        todo = todo.map(todo => {
            if (todo.id === id) {
                todo.title = edit;
            }
            return todo;
        })
        rederTodos(todo);
    }
}

const toggleStatus = (id) => {
    todo = todo.map(todo => {
        if (todo.id === id) {
            todo.status =! todo.status;
        }
        return todo;
    })
    rederTodos(todo);
}

rederTodos(todo); 