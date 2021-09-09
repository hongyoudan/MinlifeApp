<?php


namespace app\controller;

use think\facade\View;
use think\facade\Session;


use think\facade\Db;

class Number
{
    public function addnumber()
    {
        if (input('?post.qishu') && input('?post.createtime') &&
            input('?post.z1') && input('?post.z2') &&
            input('?post.z3') && input('?post.z4') &&
            input('?post.z5') && input('?post.z6') &&
            input('?post.tema') && input('?post.sum') &&
            input('?post.sumdx') && input('?post.sumds') &&
            input('?post.temads') && input('?post.temadx') &&
            input('?post.temahedx') && input('?post.temaheds') &&
            input('?post.temaweids') && input("?post.pt") &&
            input('?post.p1') && input('?post.p2') &&
            input('?post.p3') && input('?post.p4') &&
            input('?post.p5') && input('?post.p6')) {
            $data = [
                "qishu" => input('post.qishu'), "createtime" => input('post.createtime'),
                "n1" => input('post.z1'), "n2" => input('post.z2'),
                "n3" => input('post.z3'), "n4" => input('post.z4'),
                "n5" => input('post.z5'), "n6" => input('post.z6'),
                "tema" => input('post.tema'), "sum" => input('post.sum'),
                "sumdx" => input('post.sumdx'), "sumds" => input('post.sumds'),
                "temads" => input('post.temads'), "temadx" => input('post.temadx'),
                "temahedx" => input('post.temahedx'), "temaheds" => input('post.temaheds'),
                "weidx" => input('post.temaweids'), "pt" => input("post.pt"),
                "p1" => input("post.p1"), "p2" => input("post.p2"),
                "p3" => input("post.p3"), "p4" => input("post.p4"),
                "p5" => input("post.p5"), "p6" => input("post.p6")
            ];
            $result = Db::name('numbers')->insert($data);
            if ($result > 0) {
                return json(["code" => 1, 'info' => "success"]);
            } else {
                return json(["code" => 2]);
            }
        }
        return json(["code" => 0]);
    }

    public function gopreview()
    {
        if (input('?post.qishu') && input('?post.createtime') &&
            input('?post.z1') && input('?post.z2') &&
            input('?post.z3') && input('?post.z4') &&
            input('?post.z5') && input('?post.z6') &&
            input('?post.tema') && input('?post.sum') &&
            input('?post.sumdx') && input('?post.sumds') &&
            input('?post.temads') && input('?post.temadx') &&
            input('?post.temahedx') && input('?post.temaheds') &&
            input('?post.temaweids') && input("?post.pt") &&
            input('?post.p1') && input('?post.p2') &&
            input('?post.p3') && input('?post.p4') &&
            input('?post.p5') && input('?post.p6')) {
            $data = [
                "qishu" => input('post.qishu'), "createtime" => input('post.createtime'),
                "n1" => input('post.z1'), "n2" => input('post.z2'),
                "n3" => input('post.z3'), "n4" => input('post.z4'),
                "n5" => input('post.z5'), "n6" => input('post.z6'),
                "tema" => input('post.tema'), "sum" => input('post.sum'),
                "sumdx" => input('post.sumdx'), "sumds" => input('post.sumds'),
                "temads" => input('post.temads'), "temadx" => input('post.temadx'),
                "temahedx" => input('post.temahedx'), "temaheds" => input('post.temaheds'),
                "weidx" => input('post.temaweids'), "pt" => input("post.pt"),
                "p1" => input("post.p1"), "p2" => input("post.p2"),
                "p3" => input("post.p3"), "p4" => input("post.p4"),
                "p5" => input("post.p5"), "p6" => input("post.p6"), "id" => 1,
            ];
            $result = Db::name('numbers_preview')->update($data);
            if ($result > 0) {
                return json(["code" => 1]);
            } else {
                return json(["code" => 2]);
            }
        }
        return json(["code" => 0]);
    }

    public function findSpecialNumberTrend($rannum = 10, $periods = 10)
    {
        $info = Db::table('tp_numbers')->order('id ins')->limit(10)->select();
        $numbers = [];
        $issuse = [];
        foreach ($info as $item) {
            array_push($numbers, $item["tema"]);
            array_push($issuse, $item["qishu"]);
        }

        return json(["result" => ["data" => ["numbers" => $numbers, "issues" => $issuse]]]);

    }
    public function getpreview(){
        $info = Db::table('tp_numbers_preview')->where("id",1)->find();
        return json($info);
    }

 
    public function findPreDrawYearAll($rannum = 10, $periods = 10)
    {
        $info = Db::table("tp_numbers")->select();
        $list = [];
        foreach ( $info as $item ){
            $year =  substr($item['createtime'],0,4);
            if(in_array($year,$list)){ continue;}
            $list[count($list)] = intval(substr($item['createtime'],0,4));
        }
       $len = count($list);
        for ($i = 0; $i < $len -1; $i++) {//循环对比的轮数
            for ($j = 0; $j < $len - $i - 1; $j++) {//当前轮相邻元素循环对比
                if ($list[$j] < $list[$j + 1]) {//如果前边的大于后边的
                    $tmp = $list[$j];//交换数据
                    $list[$j] = $list[$j + 1];
                    $list[$j + 1] = $tmp;
                }
            }
        } 
        return json(["errorCode" => 0, "result" => ["businessCode" => 0, "data" => $list]]);
    }

    public function findSmallSixHistory($rannum = 10, $periods = 10)
    {
//        return json(["result"=>["data"=>["numbers"=>[1,2,3,32,48,35,23,4,10,16,50,34,32,19,28,15,10,27,34,37],"issues"=>[221,222,223,224,225,226,227,228,229,230,231,232,233,234,235,236,237,238,239,240] ]]]);
        $url = 'http://www.xjp888.net/findSmallSixHistory.asp';
        $params = array('name' => '张三');
        $url = "{$url}?" . http_build_query($params);
        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'GET');
        curl_setopt($ch, CURLOPT_TIMEOUT, 60);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $params);
        $result = curl_exec($ch);
        curl_close($ch);
        $resp_json = json_decode($result);
        return json($resp_json);
    }

 
    public function getlast()
    {
        $info = Db::table('tp_numbers')->order('createtime', 'desc')->limit(1)->find();
        $recordtime = strtotime($info["createtime"]);
        $nowtime =  strtotime("now");
        if($recordtime>$nowtime){
            $info = Db::table('tp_numbers')->order('createtime', 'desc')->limit(2)->select();
            $yestday = $info[1];
            return  json(["code"=>0,"info"=>$yestday]);
        }else{
            return json(["info"=>$info,"code"=>1]);
        }
          
    }

    public function delnumber()
    {
        if (input("?post.qishu")) {
            $info = Db::table('tp_numbers')->where('qishu', input("post.qishu"))->delete();
            if ($info != 0) {
                return json(["code" => 1]);
            } else {
                return json(["code" => 2]);
            }
        } else {
            return json(["code" => 0]);
        }
    }


}