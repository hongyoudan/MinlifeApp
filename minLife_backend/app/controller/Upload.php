<?php


namespace app\controller;


use think\facade\Filesystem;
use think\facade\Request;
use think\facade\Session;
use think\facade\View;

class Upload
{
    public function index()
    {

        return View::fetch('index');

    }

    public function upload()
    {
        if(Session::get("logined") == true) {
            if (request()->isPost()) {
                $file = request()->file('image');
                // 移动到框架应用根目录/public/uploads/ 目录下
                if ($file) {
                    $info = Filesystem::putFile('topic', $file);
                    if ($info) {
                        // 成功上传后 获取上传信息
                        // 输出 jpg
                        // 输出 20160820/42a79759f284b767dfcb2a0197904287.jpg
                        // 输出 42a79759f284b767dfcb2a0197904287.jpg
                        echo $info;
                    } else {
                        // 上传失败获取错误信息
                        echo $file->getError();
                    }
                }
            }
        }
    }

}