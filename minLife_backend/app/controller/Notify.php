<?php


namespace app\controller;


use think\facade\Db;
use think\facade\Session;

class Notify
{


    public function getNotify()
    {
        $info = Db::table('tp_notify')->where("id",1)->find();
        return json($info);
    }
    public function setNotify()
    {
        if(Session::get("logined") == true) {
            if (input("?post.notify"))
            {
                $info = Db::table('tp_notify')->where("id",1)->update(["id"=>1,"info"=>input("post.notify")]);
                return json(["code"=>1]);
            }
            return json(["code"=>2]);
        }
        return json(["code"=>0]);
    }

}