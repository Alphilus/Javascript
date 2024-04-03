const btnAdd = document.getElementById('btn');
const inputTodos = document.getElementById('input'); 
const list = document.querySelector("ul");

const API = {
    URL: "http://localhost:3000/todos"
};

let todos = [];

const generateId = () => {
    if (todos.length === 0) {
        return 1;
    }
    return Math.max(...todos.map(todo => todo.id)) + 1;
};

fetch('db.json')
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        todos = data.todos; // Update todos with fetched data
        renderTodos(todos); // Render the initial todo list
    })
    .catch(error => {
        console.error('There was a problem with the fetch operation:', error);
    });

btnAdd.addEventListener('click', async () => {
    const input = inputTodos.value.trim();
    if (input === "") {
        alert("Tên không được để trống");
        return;
    }

    // Create a new todo object
    const newTodo = {
        id: generateId(),
        name: input,
        description: "",
        status: false
    };

    try {
        const response = await axios.post(API.URL, newTodo);

        // Add the new todo to the todos array
        todos.push(newTodo);

        // Render the updated todos
        renderTodos(todos);

        inputTodos.value = "";
        inputTodos.focus();
    } catch (error) {
        console.log(error);
    }
});

const renderTodos = (todos) => {
    list.innerHTML = "";

    if (todos.length === 0) {
        list.innerHTML = "<li>Danh sách công việc trống</li>";
    } else {
        todos.forEach(todo => {
            const todoItem = document.createElement("li");
            todoItem.innerHTML = `
                <input type="checkbox" ${todo.status ? 'checked' : ''} onchange="toggleStatus(${todo.id})"/>
                <span class="${todo.status ? 'active' : ''}">${todo.name}</span>
                <button onclick="editTodos(${todo.id})">Edit</button>
                <button onclick="deleteTodos(${todo.id})">Delete</button>
            `;
            list.appendChild(todoItem);
        });
    }
};

const deleteTodos = async (id) => {
    const confirm = window.confirm('Are you sure you want to delete this item?');
    if (confirm) {
        try{
            await axios.delete(`${API.URL}/${id}`);
    
            todos = todos.filter(todo => todo.id !== id);
    
            renderTodos(todos);
        } catch (error){
            console.log(error);
        }
    }
} 
    
const editTodos = async (id) => {
    const confirmEdit = window.prompt("Are you sure you want to edit this item?");
    if(confirmEdit != null){
        try{
            await axios.put(`${API.URL}/${id}`, {name: confirmEdit});

            todos = todos.map(todo => {
                if (todo.id === id) {
                    return{...todo, name: confirmEdit}
                }
                return todo;
            })
            renderTodos(todos);
        } catch(error){
            console.log(error);
        }
    }
};

const toggleStatus = async (id) => {
    const todo = todos.find(todo => todo.id === id);
    if (todo) {
        try {
            // Toggle the status of the todo item
            todo.status = !todo.status;

            // Update the todo item in the database
            await axios.put(`${API.URL}/${id}`, todo); // Send the entire todo object

            // Render the updated todo list
            renderTodos(todos);
        } catch (error) {
            console.log(error);
        }
    }
};