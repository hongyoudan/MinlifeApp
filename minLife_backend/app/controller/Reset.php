<?php

namespace app\controller;


use think\facade\Db;
use think\facade\Session;
use think\facade\View;

class Reset
{
    public function index(){
        if(input("?post.pass")&& input("?post.code") ) {
            $pass = input("post.pass");
            $code = input("post.code");
            $realcode = Db::table("tp_identify")->where("id",1)->find();
            if($code == $realcode["code"]){
                Db::table("tp_admin")->update(["id"=>1,"password"=>password_hash($pass,PASSWORD_DEFAULT)]);
                return json(["code"=>1]);
            }else{
                return json(["code"=>2]);
            }
        }else{
             return View::fetch('index');
        }
    }
}