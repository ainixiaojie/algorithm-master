// 查询列表接口
const getAlertPage = (params) => {
    return $axios({
        url: '/alert/page',
        method: 'get',
        params
    })
}


// 删除数据接口
const deleteAlert= (ids) => {
    return $axios({
        url: '/alert',
        method: 'delete',
        params: {ids}
    })
}

// 修改接口
const editAlert = (params) => {
    return $axios({
        url: '/alert',
        method: 'put',
        data: {...params}

    })
}

// 新增接口
const addAlert = (params) => {
    return $axios({
        url: '/alert',
        method: 'post',
        data: {...params}
    })
}


// 查询详情
const queryAlertById = (id) => {
    return $axios({
        url: `/alert/${id}`,
        method: 'get'
    })
}


// 获取菜品分类列表
const getReasonList = (params) => {
    return $axios({
        url: '/alertClassification/list',
        method: 'get',
        params
    })
}




// 文件down预览
const commonDownload = (params) => {
    return $axios({
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        url: '/common/download',
        method: 'get',
        params
    })
}

