<?php


namespace app\controller;


use think\facade\Db;
use think\facade\Session;
use think\facade\View;
class Cate
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
    public function getcate()
    {
        $info = Db::table("cate")->select();
        return json(["info"=>$info]);
    }
    public function addcate()
    {
        if(input("?post.name")){
            $data["name"] = input("post.name");
            $data["info"] = input("post.info");
            $info = Db::table("cate")->insert($data);
            return json(["info"=>$info]);
        }
    }
    public function delcate()
    {
        if(input("?post.id")){
            $id = input("post.id");
            $info = Db::table("cate")->where("id",$id)->delete();
            return json(["info"=>$info]);
        }
    }
}