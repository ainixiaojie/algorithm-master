// 查询列表接口
const getNotificationPage = (params) => {
    return $axios({
        url: '/DeviceNotification/page',
        method: 'get',
        params
    })
}

// 编辑页面反查详情接口
const queryNotificationById = (id) => {
    return $axios({
        url: `/DeviceNotification/${id}`,
        method: 'get'
    })
}

// 删除当前列的接口
const deleteNotification = (id) => {
    return $axios({
        url: '/DeviceNotification',
        method: 'delete',
        params: {id}
    })
}

// 修改接口
const editNotification = (params) => {
    return $axios({
        url: '/DeviceNotification',
        method: 'put',
        data: {...params}
    })
}

// 新增接口
const addNotification = (params) => {
    return $axios({
        url: '/DeviceNotification',
        method: 'post',
        data: {...params}
    })
}