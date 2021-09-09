<?php


namespace app\controller;


use think\facade\Db;
use think\facade\View;
class Product
{
    public function getproduct()
    {
        if(input("?get.id")){
            $info = Db::table("product")->where("id",input("get.id"))-> select();
            return json(["cate"=>input("get.cate") ,"info"=>$info]);

        }
        $cate = input("?get.cate");
        if($cate != null)
            $info = Db::table("product")->where("cate",input("get.cate"))-> select();
        else
            $info = Db::table("product")-> select();
        return json(["cate"=>input("get.cate") ,"info"=>$info]);
    }
    public function addproduct()
    {
        if(
            input("?post.addname") &&
            input("?post.addimgurl") &&
            input("?post.addprice") &&
            input("?post.addcate") &&
            input("?post.addinfo")
        ){
            $data["imgurl"] = input("post.addimgurl");
            $data["name"] = input("post.addname");
            $data["price"] = input("post.addprice");
            $data["cate"] = input("post.addcate");
            $data["info"] = input("post.addinfo");
            $info = Db::table("product")->insert($data);
            return json(["code"=>1,"info"=>$info]);
        }else {
            return json(["code"=>0]);

        }
    }
    public function delproduct()
    {
        if(input("?post.id")){
            $id = input("post.id");
            $info = Db::table("product")->where("id",$id)->delete();
            return json(["info"=>$info]);
        }
    }

}