// 查询列表接口
const getDeviceTypePage = (params) => {
  return $axios({
    url: '/device/page',
    method: 'get',
    params
  })
}



// 删除数据接口
const deleteDevice = (ids) => {
  return $axios({
    url: '/device',
    method: 'delete',
    params: { ids }
  })
}

// 修改接口
const editDevice = (params) => {
  return $axios({
    url: '/device',
    method: 'put',
    data: { ...params }

  })
}

// 新增接口
const addDevice = (params) => {
  return $axios({
    url: '/device',
    method: 'post',
    data: { ...params }
  })
}


/**
 * 根据id查寻设备3
 * @param id
 * @returns {*}
 */
const queryDeviceById = (id) => {
  return $axios({
    url: `/device/${id}`,
    method: 'get'
  })
}


/**
 * 获取设备类型分类
 * @param params
 * @returns {*}
 */
const getCategoryList = (params) => {
  return $axios({
    url: '/deviceType/list',
    method: 'get',
    params
  })
}
/**
 * 获取设备故障分类
 * @param params
 * @returns {*}
 */
const getNotificationList = (params) => {
  return $axios({
    url: '/DeviceNotification/list',
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

