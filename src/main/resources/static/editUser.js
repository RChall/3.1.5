$('#editModal').on('show.bs.modal', ev => {
    let button = $(ev.relatedTarget);
    let idEdit = button.data('user-edit-id');
    editModalView(idEdit);
})

$(async function () {
    await editCurrentUser();
});

async function editModalView(idEdit) {
    let userEdit = await getUserById(idEdit);
    let formEdit = document.getElementById('edit-form-modal');
    formEdit.id.value = idEdit
    formEdit.username.value = userEdit.username
    formEdit.password.value = userEdit.password
    formEdit.name.value = userEdit.name
    formEdit.lastName.value = userEdit.lastName
    formEdit.country.value = userEdit.country
    formEdit.age.value = userEdit.age
    formEdit.enabled.value = true
    $('#roleInputForUpdated').empty()
    await fetch('http://localhost:8080/api/allroles')
        .then(r => r.json())
        .then(roles => {
            roles.forEach(role => {
                console.log(role)
                let selectedRole = false;
                for (let i = 0; i < userEdit.roles.length ; i++) {
                    if (userEdit.roles[i].roleNaming === role.roleNaming) {
                        selectedRole = true;
                        break;
                    }
                }
                let element = document.createElement('option');
                element.text = role.roleNaming;
                element.value = role.id;
                console.log(element)
                if (selectedRole) element.selected = true;
                $('#roleInputForUpdated')[0].appendChild(element);
            })
        })
        .catch((error) => {
            alert(error);
        })
}


async function editCurrentUser() {
    let formEdit = document.forms['edit-form-modal'];
    formEdit.addEventListener('submit', async (e) => {
        e.preventDefault();
        let editUserRoles = [];
        for (let i = 0; i < formEdit.roles.options.length ; i++) {
            if (formEdit.roles.options[i].selected) editUserRoles.push({
                id: formEdit.roles.options[i].value,
                roleNaming: formEdit.roles.options[i].role,
            });
        }
        await fetch('http://localhost:8080/api/update', {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: formEdit.id.value,
                username: formEdit.username.value,
                password: formEdit.password.value,
                name: formEdit.name.value,
                lastName: formEdit.lastName.value,
                country: formEdit.country.value,
                age: formEdit.age.value,
                roles: editUserRoles,
                enabled: true
            })
        })
            .then(() => {
                formEdit.reset();
                $('#close-edit-modal').click();
                getAllUsers();
            })
            .catch((error) => {
                alert(error);
            })
    })
}


