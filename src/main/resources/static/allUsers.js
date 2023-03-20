$(async function () {
    await getAllUsers();
});

async function getAllUsers() {
    const userList = $('#main-users-table');
    userList.empty();
    fetch('http://localhost:8080/api')
        .then(r => r.json())
        .then(data => {
            data.forEach(user => {
                let users = `$(
                    <tr>
                        <td>${user.username}</td>
                        
                        <td>${user.name}</td>
                        <td>${user.lastName}</td>
                        <td>${user.country}</td>
                        <td>${user.age}</td>
                        <td>${user.role}</td>
                        <td>${user.enabled}</td>
                       
                        
                        <td>
                            <input type="submit" class="btn btn-info js-open-modal" data-modal="data-modal" data-toggle="modal"
                            data-target="#editModal" value="Изменить" data-user-edit-id="${user.id}" >
                        </td>
                        <td>
                            <input type="submit" class="btn btn-danger" data-toggle="modal"
                                 data-target="#deleteModal"  data-user-delete-id="${user.id}"
                                value="Удалить">
                        </td>
                    </tr>)`;
                userList.append(users);
            })
        })
}




