async function getPrincUser() {
    const res = await fetch('http://localhost:8080/api/principaluser');
    const princUser = await res.json();

    console.log(princUser)
    getPrincUserToBar(princUser)
    getPrincUserToHtml(princUser)

}

window.addEventListener('DOMContentLoaded', getPrincUser)

function getPrincUserToHtml({id,authorities, username, password, name, lastName, country, age, enabled}) {
    const userList = document.getElementById('ineligible-user');

    userList.insertAdjacentHTML('beforeend', `
<tr id="user${id}">
  <td>${username}</td>
  <td>${name}</td>
  <td>${lastName}</td>
  <td>${country}</td>
  <td>${age}</td>
  
</tr>`)
}

function getPrincUserToBar({id,authorities, username, password, name, lastName, country, age, enabled}) {
    const userList = document.getElementById('navbar-user-info');

    userList.insertAdjacentHTML('beforeend', `
 <td >${username}</td>, роли:
        <td >${JSON.stringify(authorities).substring(16,JSON.stringify(authorities).length - 4)}</td>`)
}
