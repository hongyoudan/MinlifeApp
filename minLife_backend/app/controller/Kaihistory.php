<?php


namespace app\controller;


use think\facade\Db;
use think\facade\View;

class Kaihistory
{
    
    public function index()
    {
        if (input("?get.year")&& input("?get.pageindex")) {
            $pageindex = 1;
             
            $pageindex = input("get.pageindex");
            $total = Db::table("tp_numbers")->where('createtime', 'like', "2020" . '%')->count();

         
            $info = Db::table("tp_numbers")->where('createtime', 'like', input("get.year") . '%')->page($pageindex,40)->select();
            for($ind =0 ;$ind < sizeof($info);$ind++){
                $nowtime =  strtotime("now");
                $itemtime = strtotime($info[$ind]["createtime"]);
                if($itemtime+360 > $nowtime){
                    unset($info[$ind]);
                    $total--;
                }
            } 
            if( $total %40 > 0)
                $totalpage= $total/40+1;
            else 
                $totalpage= $total/40;
            View::assign('pageindex',  $pageindex);    
            View::assign('totalpage',$totalpage  );      
            View::assign('total',$total  );      
            View::assign('info', $info);
            return View::fetch('index');
        }else{ 
            return View::fetch('index');
        }  
    }
    public function getHistory () {
        if (input("?get.year") && input("?get.pageindex")) {
            $total = Db::table("tp_numbers")->where('createtime', 'like', input("get.year") . '%')->count();
            $pageindex = 0;
 
            $pageindex = intval (input("get.pageindex"));
            $info = Db::table("tp_numbers")->where('createtime', 'like', input("get.year") . '%')->order("qishu","desc")->limit($pageindex*40,40)->select();
            for($ind =0 ;$ind < sizeof($info);$ind++){
                $nowtime =  strtotime("now");
                $itemtime = strtotime($info[$ind]["createtime"]);
                if($itemtime+360 > $nowtime){
                    unset($info[$ind]);
                    $total--;
                }
            }
            if( $total %40 > 0)
                $totalpage= $total/40+1;
            else 
                $totalpage= $total/40;
            return json(["info"=>$info,"pageindex"=>$pageindex,"totalpage"=>$totalpage,"total"=>$total]);
        }  else {
            return "notthing there";
        }
    }
}