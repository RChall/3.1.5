$(async function () {
    await addUser();
});


async function addUser() {
    await fetch('http://localhost:8080/api/allroles')
        .then(r => r.json())
        .then(roles => {
            roles.forEach(role => {
                let elementSave = document.createElement("option");
                elementSave.text = role.roleNaming;
                elementSave.value = role.id;
                $('#add-new-roleSelect')[0].appendChild(elementSave);
            })
        })

    let formSave = document.forms["add-new-user-form"];
    formSave.addEventListener("submit", function (event) {
        event.preventDefault();
        let editUserRoles = [];
        for (let i = 0; i < formSave.roles.options.length; i++) {
            if (formSave.roles.options[i].selected) editUserRoles.push({
                id: formSave.roles.options[i].value,
                roleNaming: formSave.roles.options[i].role,
            });
        }
        fetch('http://localhost:8080/api/add', {

            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id:100500,
                username: formSave.username.value,
                password: formSave.password.value,
                name: formSave.name.value,
                lastName:formSave.name.value,
                country: formSave.country.value,
                age: formSave.age.value,
                roles: editUserRoles,
                enabled:true,

            })
        })
            .then(() => {
                formSave.reset();
                getAllUsers();
                $('#home-tab').click();
            })
            .catch((error) => {
                alert(error);
            })
    })
}