<?php


namespace app\controller;

use think\facade\Db;
use think\facade\Session;
use think\facade\View;
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;

class Email
{
    public function index()
    {
        $str = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
        $len = strlen($str)-1;
        $randstr = '';
        for ($i=0;$i<6;$i++) {
            $num=mt_rand(0,$len);
            $randstr .= $str[$num];
        }

        Db::table("tp_identify")->update(["id"=>1,"code"=>$randstr]);
        $emailinfo = Db::table("tp_mail")->where("id",1)->find();
        $toemail = $emailinfo["email"];
        $mail = new PHPMailer();
        $mail->isSMTP();        // 使用SMTP服务
        $mail->CharSet = "utf8";// 编码格式为utf8，不设置编码的话，中文会出现乱码
        $mail->Host = "smtp.exmail.qq.com";// 发送方的SMTP服务器地址
        $mail->SMTPAuth = true;// 是否使用身份验证
        $mail->Username = "xiaopu@andsmind.com";// 发送方的163邮箱用户名，就是你申请163的SMTP服务使用的163邮箱</span><span style="color:#333333;">
        $mail->Password = "Pkpk123123";// 发送方的邮箱密码，注意用163邮箱这里填写的是“客户端授权密码”而不是邮箱的登录密码！</span><span style="color:#333333;">
        $mail->SMTPSecure = "ssl";// 使用ssl协议方式</span><span style="color:#333333;">
        $mail->Port = 465;// 163邮箱的ssl协议方式端口号是465/994
        $mail->setFrom("xiaopu@andsmind.com", "xiaopu");// 设置发件人信息，如邮件格式说明中的发件人，这里会显示为Mailer(xxxx@163.com），Mailer是当做名字显示
        $mail->addAddress($toemail, '');// 设置收件人信息，如邮件格式说明中的收件人，这里会显示为Liang(yyyy@163.com)
        $mail->addReplyTo("xiaopu@andsmind.com", "Reply");// 设置回复人信息，指的是收件人收到邮件后，如果要回复，回复邮件将发送到的邮箱地址
        $mail->Subject = "重置密码验证码:";// 邮件标题
        $mail->Body = "您的验证码是:" . $randstr . "点击可以跳转回登录页: http://tempvsd.andsmind.com/reset";// 邮件正文
        //$mail->AltBody = "This is the plain text纯文本";// 这个是设置纯文本方式显示的正文内容，如果不支持Html方式，就会用到这个，基本无用

        if (!$mail->send()) {     // 发送邮件
//            return 0;
            return $mail->ErrorInfo;
        } else {
            return 1;
        }
    

        // echo "Mail Sent.";
        // $info = Db::table('tp_dongshi')->where('id', 1)->find();
        // View::assign('info',$info);
        // return View::fetch('index');
    }
    public function setemail () {
        if(Session::get("logined")== true){
            if(input("?post.email")){
                $mail = input("post.email");
                Db::table("tp_mail")->update(["id"=>1,"email"=>$mail]);
                return json(["code"=>1]);
            }
        }
        return json(["code"=>0]);
    }
    public function getemail () {
        if(Session::get("logined")== true){
          {

                $info =  Db::table("tp_mail")->where("id",1)->find();
                return json(["code"=>1,"email"=>$info]);
            }
        }
        return json(["code"=>0]);
    }

}
