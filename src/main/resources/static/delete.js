function del(userId) {
    fetch(`http://localhost:8080/admin/${userId}`)
        .then(response => response.json())
        .then(user => {
            $('#userIdDelete').attr('value', `${user.id}`)
            $('#userFirstNameDelete').attr('value', `${user.firstName}`)
            $('#userLastNameDelete').attr('value', `${user.lastName}`)
            $('#userAgeDelete').attr('value', `${user.age}`)
            $('#userLoginDelete').attr('value', `${user.login}`)
            $('#userPasswordDelete').attr('value', `${user.password}`)
            $('#userRoleDelete').attr('value', `${user.rolesToString}`)
            $('#btnDelete').attr('onclick', `deleteUser(${user.id})`)
        })
}

function deleteUser(userId){
    console.log('deleteUser activated')
    fetch(`http://localhost:8080/admin/${userId}`,{
        method: 'DELETE'
    }).then(showAllUsers)
}