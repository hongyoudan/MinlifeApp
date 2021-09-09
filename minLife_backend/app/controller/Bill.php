<?php


namespace app\controller;


use think\facade\Db;
use think\facade\Session;
use think\facade\View;
class Bill
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
    public function getallbill()
    {
        $info = Db::table("bill") ->select();
        return json(["info"=>$info]);

    }
    public function getbill()
    {
        if(input("?get.username")){
            if(input("?get.state")) {
                $info = Db::table("bill")->where([
                    "username"=>input ("get.username"),
                    "state"=>input ("get.state")
                ])->select();
                return json(["info" => $info]);
            }else{
                $info = Db::table("bill")->where("username", input("get.username"))->select();
                return json(["info" => $info]);
            }
        }
    }
//    public function billproduct()
//    {
//        if(input("?get.product_id") && input("?get.username")  ){
//
//            $product_info = Db::table("product")->where("id",input("get.product_id"))->find();
//            $data["username"] = input("get.username");
//            $data["total"] = $product_info["price"];
//            $data["cate"] = $product_info["cate"];
//            $data["imgurl"] = $product_info["imgurl"];
//            $data["product"] = $product_info["name"];
//            $data["state"] = "待付款";
//            $info = Db::table("bill")->insert($data);
//            $userId = Db::table('bill')->getLastInsID();
//
//            return json(["code"=>1,"info"=>$userId]);
//        }
//    }

    public function billproduct()
    {
        if(input("?get.product_id") && input("?get.username") && input("?get.count")  ){
            $product_info = Db::table("product")->where("id",input("get.product_id"))->find();
            $data["username"] = input("get.username");
            $data["total"] = $product_info["price"] * (int) input("get.count");
            $data["cate"] = $product_info["cate"];
            $data["imgurl"] = $product_info["imgurl"];
            $data["product"] = $product_info["name"];
//            $data["state"] = "已下单";
            $data["state"] = "待付款";

            $info = Db::table("bill")->insert($data);
            $userId = Db::table('bill')->getLastInsID();

            return json(["code"=>1,"info"=>$userId]);
        }
    }


    public function sendpackage()
    {
        if(input("?post.id")   ){
            $bill_id = input("post.id");

            $info = Db::table("bill") ->update(["id"=>$bill_id,"state"=>"已发货"]);
            return json(["info"=>$info]);
        }
    }
    public function finish()
    {
        if(input("?get.id")   ){
            $bill_id = input("get.id");

            $info = Db::table("bill") ->update(["id"=>$bill_id,"state"=>"已完成"]);
            return json(["info"=>$info]);
        }
    }
    public function paybill()
    {
        if(input("?get.id")   ){
            $bill_id = input("get.id");
            $info = Db::table("bill")->where("id",$bill_id)->update(["state"=>"已付款"]);
            return json(["code"=>1,"info"=>$info]);
        }
    }
    public function delbill()
    {
        if(input("?post.id")){
            $id = input("post.id");
            $info = Db::table("bill")->where("id",$id)->delete();
            return json(["info"=>$info]);
        }
    }

}