let token = "";
async function register(){
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
const response = await fetch("http://localhost:8080/auth/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password })
    });
    const message = await response.text();
    document.getElementById("auth-message").textContent = message;
}
async function login() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    const response = await fetch("http://localhost:8080/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password })
    });

    if (response.ok) {
        const data = await response.json();
        token = data.token;
        document.getElementById("auth-section").style.display = "none";
        document.getElementById("tasks-section").style.display = "block";
        loadTasks();
    } else {
        document.getElementById("auth-message").textContent = "Identifiants incorrects !";
    }
}
async function loadTasks() {
    const response = await fetch("http://localhost:8080/tasks", {
        headers: { "Authorization": "Bearer " + token }
    });

    const tasks = await response.json();
    const tasksList = document.getElementById("tasks-list");
    tasksList.innerHTML = "";

    tasks.forEach(task => {
        tasksList.innerHTML += `
            <div class="task-card">
                <div class="task-info">
                    <h3>${task.titre}</h3>
                    <p>${task.description}</p>
                    <span class="statut ${task.statut}">${task.statut}</span>
                </div>
                <div class="task-actions">
                   <button onclick="editTask(${task.id}, '${task.titre}', '${task.description}', '${task.statut}')">✏️</button>
                    <button onclick="deleteTask(${task.id})">🗑️</button>
                </div>
            </div>
        `;
    });
}
async function createTask() {
    const titre = document.getElementById("titre").value;
    const description = document.getElementById("description").value;
    const statut = document.getElementById("statut").value;

    await fetch("http://localhost:8080/tasks", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        },
        body: JSON.stringify({ titre, description, statut })
    });

    
    document.getElementById("titre").value = "";
    document.getElementById("description").value = "";

   
    loadTasks();
}
async function deleteTask(id) {
    await fetch("http://localhost:8080/tasks/" + id, {
        method: "DELETE",
        headers: { "Authorization": "Bearer " + token }
    });
    loadTasks();
}

function logout() {
    token = "";
    document.getElementById("tasks-section").style.display = "none";
    document.getElementById("auth-section").style.display = "flex";
}
function editTask(id, titre, description, statut) {
    
    document.getElementById("titre").value = titre;
    document.getElementById("description").value = description;
    document.getElementById("statut").value = statut;

    const btn = document.querySelector(".new-task-form button");
    btn.textContent = "💾 Sauvegarder";
    btn.onclick = () => updateTask(id);
}

async function updateTask(id) {
    const titre = document.getElementById("titre").value;
    const description = document.getElementById("description").value;
    const statut = document.getElementById("statut").value;

    await fetch("http://localhost:8080/tasks/" + id, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        },
        body: JSON.stringify({ titre, description, statut })
    });

   
    const btn = document.querySelector(".new-task-form button");
    btn.textContent = "➕ Ajouter";
    btn.onclick = createTask;

    
    document.getElementById("titre").value = "";
    document.getElementById("description").value = "";

    loadTasks();
}