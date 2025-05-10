// Alamat dasar endpoint REST API
const API_URL = 'http://localhost:8080/api/tasks';

// Mengambil element dari HTML
const todoInput = document.getElementById('todoInput'); // input teks tugas
const addButton = document.getElementById('addButton'); // tombol tambah tugas
const todoList = document.querySelector('.todo-list'); // tempat daftar tugas

// Mengambil semua data dari task saat halman di load
document.addEventListener('DOMContentLoaded', loadTasks);

// Button add
addButton.addEventListener('click', () => {
    const text = todoInput.value.trim(); // ambil teks dari input
    if (text) {
        createTask(text); // kirim ke backend untuk buat task baru
        todoInput.value = ''; // kosongkan input setelah dikirim
    }
});

// fungsi: Mengambil semua task dari backend (GET /api/tasks) 
function loadTasks() {
    fetch(API_URL)
    .then(res => res.json()) // ubah response menjadi JSON
    .then(tasks => {
        todoList.innerHTML = '';
        tasks.forEach(task => addTaskToDOM(task)); // tampilkan semua task ke UI
        })
        .catch(err => console.error('Gagal ambil data:', err)); // jika gagal
    }

// Fungsi: Menampilkan 1 task ke dalam tampilan (DOM)
function addTaskToDOM(task) {
    const li = document.createElement('li'); // buat element <li>
    li.className = 'todo-item'; // name class
    li.dataset.id = task.id;
 
    // isi dari setiap task: task dan tombol aksi
    li.innerHTML = `
    <input type="checkbox" class="task-check" ${task.completed ? 'checked' : ''}>
    <span class="task-text ${task.completed ? 'completed' : ''}">${task.name}</span>
    <div class="actions">
        <button class="edit-btn" title="Edit"><i class="fas fa-pen"></i></button>
        <button class="delete-btn" title="Hapus"><i class="fas fa-trash"></i></button>
    </div>
        `;
    
    // Checkbox toggle completed
    li.querySelector('.task-check').addEventListener('change', (e) => {
        updateTask(task.id, task.name, e.target.checked);
    });

    // Event klik  edit button
    li.querySelector('.edit-btn').addEventListener('click', () => {
        const newName = prompt('Edit task:', task.name); // tampilan prompt input
        if(newName && newName.trim() !== '') {
            updateTask(task.id, newName); // kirim ke backend untuk update
        }
    });

    // Event klike delete button
    li.querySelector('.delete-btn').addEventListener('click', () => {
        if (confirm('Delete this task?')) {
            deleteTask(task.id); // kirim ke backend untuk hapus
        }
    });

    todoList.appendChild(li); // tambahkan task ke daftar di halaman
}

// Fungsi: Membuat task baru (POST /api/tasks)
function createTask(name) {
    fetch(API_URL,{
        method: 'POST',
        headers: {'Content-Type': 'application/json'}, // headers JSON
        body: JSON.stringify({ name }) // kirim data ke properti name
    })
    .then(res => res.json()) // ubah response jadi JSON
    .then(newTask => addTaskToDOM(newTask)) // tampilkan task baru di halaman
    .catch(err => console.error('Failed to add task:', err));
}

// Fungsi: Mengupdate task berdasarkan ID (PUT /api/tasks/{id})
function updateTask(id, newName) {
    fetch(`${API_URL}/${id}`, {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'}, 
        body: JSON.stringify({ name: newName, completed: isCompleted }) // kirim nama baru
    })
    .then(res => res.json())
    .then(updateTask => {
        const item = document.querySelector(`li[data-id="${id}"]`); // cari item berdasarkan ID
        if (item) {
            item.querySelector('.task-text').textContent = updateTask.name;
            const textEl = item.querySelector('.task-text');
            if (updateTask.completed) {
                textEl.classList.add('completed');
            } else {
                textEl.classList.remove('completed');
            }
        }
    })
        .catch(err => console.error('Failed update', err));
}

// Fungsi: Menghapus task berdasarkan ID (DELETE /api/tasks/{id})
function deleteTask(id) {
    fetch(`${API_URL}/${id}`, {
        method: 'DELETE'
    })
    .then(() => {
        const item = document.querySelector(`li[data-id="${id}"]`);
        if (item) {
            item.remove(); // hapus dari tampilan
        }
    })
    .catch(err => console.err('Failed delete:', err));
}