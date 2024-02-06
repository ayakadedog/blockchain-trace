import request from './request.js'

//String account, String password, Integer identity
export function login(data){
    return request.post('/api/user/login',data)
}
//String account,String password,Integer identity,String uname,String himg
export function register(data){
    return request.post('/api/user/register',data)
}
export function getUser(uid){
    let url='/api/user/getUser?uid='+uid
    return request.get(url)
}
export function changePassword(uid,password){
    let url='/api/user/changePassword?uid='+uid+'&password='+password
    return request.get(url)
}
export function changeUserName(uid,uname){
    return request.get('/api/user/changeUserName?uid='+uid+'&uname='+uname)
}
export function ListAddress(uid){
    return request.get('/api/user/listAddress?uid='+uid)
}
//Integer uid,String address,String phone
export function addAddress(data){
    return request.post('/api/user/addAddress',data)
}
//Integer aid,String address,String phone,Integer uid
export function updateAddress(data){
    return request.post('/api/user/updateAddress',data)
}

export function deleteAddress(aid){
    return request.get('/api/user/deleteAddress?aid='+aid)
}

export function listUser(){
    return request.get('/api/user/listUser')
}
export function deleteUser(uid){
    return request.get('/api/user/deleteUser?uid='+uid)
}

//Integer uid,String himg
export function updateHimg(data){
    return request.post('/api/user/updateHimg',data)
}
