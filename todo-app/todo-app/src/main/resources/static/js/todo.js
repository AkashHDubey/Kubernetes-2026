document.getElementById('todo-form').addEventListener('submit', async function (e) {
    e.preventDefault(); // Prevents full page reload

    const input = document.getElementById('messageText');
    const messageText = input.value.trim();

    if (!messageText) return;

    try {
        const response = await fetch('/backend/todos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ messageText: messageText })
        });

        if (response.ok) {
            // Option A: If backend returns JSON like { messageText: "Todos" }
            const data = await response.text();
            addTodoToList(data.messageText || messageText);

            // Clear input field after successful submit
            input.value = '';
        } else {
            console.error('Failed to save todo');
        }
    } catch (error) {
        console.error('Network error:', error);
    }
});

function addTodoToList(text) {
    const list = document.getElementById('todo-list');

        // Create <li> container matching your Thymeleaf classes
        const li = document.createElement('li');
        li.className = 'bg-slate-50 p-4 rounded-xl border border-slate-100 hover:border-slate-200 transition-all flex items-center justify-between group';

        // Create <span> for the item text
        const span = document.createElement('span');
        span.className = 'text-base font-medium text-slate-700 break-all pr-4';
        span.textContent = text;

        // Assemble and append
        li.appendChild(span);
        list.appendChild(li);
}