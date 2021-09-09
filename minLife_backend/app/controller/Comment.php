<?php


namespace app\controller;
// mvc model view controller

use think\facade\Db;
use think\facade\View;
class Comment
{
    public function getcomment()
    {
         {
            $info = Db::table("comment") ->select();
            return json(["info" => $info]);
        }
    }
    public function addcomment()
    {
        if(input("?get.username")  && input("?get.comment")){
            $data["username"] = input("get.username");
            $data["productid"] = 1;
            $data["updatetime"] = date("Y/m/d  h/m/s");
            $data["comment"] = input("get.comment");
            $info = Db::table("comment")->insert($data);
            return json(["code"=>1, "info"=>$info]);
        }
    }
    public function delcate()
    {
        if(input("?post.commentid")){
            $id = input("post.commentid");
            $info = Db::table("comment")->where("id",$id)->delete();
            return json(["info"=>$info]);
        }
    }
}