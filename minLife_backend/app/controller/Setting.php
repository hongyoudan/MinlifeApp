<?php


namespace app\controller;


use think\facade\Db;
use think\facade\Session;
use think\facade\View;
class Setting
{
    public function index()
    {
        if(Session::get("logined") == true){
            return View::fetch('index');
        }else{
            View::assign('name','ThinkPHP');
            return redirect("/admin");
        }
    }
    public function getposter()
    {
        $info = Db::table("setting") ->select();
        return json(["info"=>$info]);

    }

    public function addposter()
    {
        if(input("?post.imgurl")   ){
            $data["imgurl"] = input("post.imgurl");

            $info = Db::table("setting")->insert($data);
            return json(["info"=>$info]);
        }
    }

    public function delposter()
    {
        if(input("?post.id")){
            $id = input("post.id");
            $info = Db::table("setting")->where("id",$id)->delete();
            return json(["info"=>$info]);
        }
    }

}