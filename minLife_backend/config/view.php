<?php
// +----------------------------------------------------------------------
// | 设置
// +----------------------------------------------------------------------

return [
    // 引擎类型使用Think
    'type'          => 'Think',
    // 默认渲染规则 1 解析为小写+下划线 2 全部转换小写 3 保持操作方法
    'auto_rule'     => 1,
    // 目录名
    'view_dir_name' => 'view',
    // 后缀
    'view_suffix'   => 'html',
    // 文件名分隔符
    'view_depr'     => DIRECTORY_SEPARATOR,
    // 引擎普通标签开始标记
    'tpl_begin'     => '{',
    // 引擎普通标签结束标记
    'tpl_end'       => '}',
    // 标签库标签开始标记
    'taglib_begin'  => '{',
    // 标签库标签结束标记
    'taglib_end'    => '}',
    'tpl_replace_string' =>array(
        '{__STATIC__}'=>'/static',
        '{__CSS__}'=>'/static/css',
        '{__JS__}'=>'/static/jieshaoadmin',
        '{__IMG__}'=>'/static/img'
    )
];
