$('#deleteModal').on('show.bs.modal', ev => {
    let button = $(ev.relatedTarget);
    let idDelete = button.data('user-delete-id');
    deleteModalView(idDelete);
})

async function getUserById(id) {
    let url = 'http://localhost:8080/api/users/' + id;
    let response = await fetch(url);
    return await response.json();
}
async function deleteModalView(idDelete) {
    let userDelete = await getUserById(idDelete);
    let formDelete = document.getElementById('delete-form-modal');
    formDelete.id.value = idDelete
    formDelete.username.value = userDelete.username
    formDelete.password.value = userDelete.password
    formDelete.name.value = userDelete.name
    formDelete.lastName.value = userDelete.lastName
    formDelete.country.value = userDelete.country
    formDelete.age.value = userDelete.age
      $('#roleInputForDelete').empty()

    await fetch('http://localhost:8080/api/allroles')
        .then(r => r.json())
        .then(roles => {
            roles.forEach(role => {
                let selectedRoleDelete = false;
                for (let i = 0; i < userDelete.roles.length; i++) {
                    if (userDelete.roles[i].roleNaming === role.roleNaming) {
                        selectedRoleDelete = true;
                        break;
                    }
                }
                let element = document.createElement('option');
                element.text = role.roleNaming;
                element.value = role.id;
                if (selectedRoleDelete) element.selected = true;
                $('#roleInputForDelete')[0].appendChild(element);
            })
        })
        .catch((error) => {
            alert(error);
        })
}

async function deleteUser() {
    let formDelete = document.getElementById('delete-form-modal');
    formDelete.addEventListener('submit', async (e) => {
        e.preventDefault();
        const dataDelete = new FormData(e.target);

        let url = 'http://localhost:8080/api/delete/' + dataDelete.get('id');
        fetch(url, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            }
        })
            .then(() => {
                $('#cancel-to-delete-modal').click();
                getAllUsers();
            })

            .catch((error) => {
                alert(error);
            })
    })
}

$(async function () {
    deleteUser();
});