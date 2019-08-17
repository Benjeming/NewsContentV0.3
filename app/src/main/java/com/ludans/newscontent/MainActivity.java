package com.ludans.newscontent;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import ViewPager.NewsTitlesView;
import domain.NewsBean;
import domain.NewsBean1;

public class MainActivity extends AppCompatActivity {

    private ArrayList<NewsBean1> mData;
    private FragmentManager fManager;
    private long exitTime = 0;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fManager = getSupportFragmentManager();
        initView();
        initData();
    }

    private void initData() {
        mData = new ArrayList<>();
        String json = "[{\n" +
                "        \"cotent\": \"今年第9号台风“利奇马”8月10日在我国浙江登陆，浙江、江苏、山东等地出现大风、暴雨天气，造成大面积菜田积水，部分菜田发生涝灾，局部地区蔬菜设施淹水，对当前蔬菜生产造成不利影响。各地要结合蔬菜生产实际，加强技术指导，采取有效措施，降低灾害损失，尽快恢复生产。 一是及时排涝降渍。台风暴雨过后，要及时清沟理墒，快速排除积水，减少淹渍时间，防止长时间积水和雨水倒灌造成蔬菜植株烂根。设施内积水的，要赶快把积水抽走，并利用棚前沟或新挖棚前沟，集中排水。 二是加强土壤管理。田间积水排掉后，及时浇灌一遍清水，为根系提供氧气，即“涝浇园”。同时，及时进行土壤表层松土，打破土壤板结，改善通气性，注意松土不要过深。排水过后，土壤中病菌较多，土壤通透性较差，为防止植株在后面的生长过程中出现死棵现象，可选用防治根腐病及枯萎病或软腐病的药剂进行灌根或冲施。对洪水浸泡时间过长，不能恢复正常生长，失去抢救价值的菜地，应及时拉秧清园，并进行土壤消毒，减少土壤中的病菌滋生蔓延。 三是做好植株整理。对于倒伏的蔬菜植株，要及时扶正、理顺，重新绑好藤蔓，尽量不要损伤根系，并适时培土护根。要清除病株、病叶、老叶等，适当剪枝、打叶。淹水后遇到晴天，应覆盖遮阳网等进行遮阴，减少水分蒸腾，防止因缺氧根系吸水功能下降导致的植株萎蔫。如蔬菜植株及叶片上面有较多的泥水，影响植株正常光合作用时，要及时清洗干净，可用喷雾器进行清洗，覆着较多的叶片可多次连续冲洗。 四是强化施肥管理。土壤经过雨水浸泡后，会出现肥料随水流失的情况，导致土壤肥力降低，为避免出现植株营养不足，应少量多次施用肥料，并增加生物菌以活化土壤，同时辅以叶面追肥，如喷施磷酸二氢钾等。若叶片出现缺钙症状时，可叶面喷施0.5%的氯化钙水溶液2-3次，每次间隔7-10天。出现缺铁症状时，可叶面喷施0.2%-0.5%的硫酸亚铁或0.5%氨基酸铁水溶液，间隔7-10天1次，连喷2-3次。对于已经施好底肥准备定植的菜田，建议重施底肥，并加施生物菌肥，进行深翻，以保证随后定植蔬菜正常生长。 五是加强病害防治。要注意防治霜霉病、疫病等高湿性病害，蔓枯病、炭疽病等叶斑类病害，以及角斑病、疮痂病等细菌性病害。可针对不同的病害选用预防和治疗性杀菌剂混配应用，用药浓度切忌过高，引起病害。 （农业农村部水稻专家指导组 全国农业技术推广服务中心）\",\n" +
                "        \"title\": \"“利奇马”台风过后加强蔬菜生产管理技术指导意见\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"cotent\": \"要养好鸡，饲料的搭配很重要，而除了常规的饲料种类之外，还可以搭配一些保健料，起到促进鸡生长发育、增强抵抗力的作用。 　　健胃料即硫酸钠。在鸡的日粮中加入0.25%的硫酸钠，具有健胃增加食欲和刺激代谢的功能，不仅可预防鸡胃肠道疾病，还可提高肉蛋品质。 　　吸附料即木炭末。在每只鸡日粮中加入0.3克木炭末，具有清理胃肠，吸附肠内有害物质的功能。 　　刺激料即姜、葱、蒜、辣椒。每只鸡日粮中适当加入刺激料，可使鸡增加食欲、增强抗病力、减少疾病。（综合）\",\n" +
                "        \"title\": \"养鸡备点儿保健料\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"cotent\": \"公鸡在6周龄睾丸中出现初级精开细胞，10周龄出现次级精母细胞，12周龄次级精母细胞分裂为精细胞，后变为精子，20周龄达到性成熟。22周龄以后可以配种，方能得到较高的种蛋受精率。 　　公母鸡过早配种受精率低，只有当公母鸡处于同样的性活动旺盛期，种蛋受精率最高。种公鸡从22周龄用于配种，可以一直使用到72周龄，其受精率仍不降低。种公鸡可使用3年。 　　种母鸡从26周龄编群配种、采种蛋，再养48周淘汰。在此日龄范围内，种蛋受精率可高达86.3%以上。育种用优秀母鸡可以使用2-3年。\",\n" +
                "        \"title\": \"种鸡淘汰有时间\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"cotent\": \"在夏季，高温潮湿，蚊虫肆虐，许多猪场的猪身上会出现红斑点，有的猪红斑比较大，有的红斑比较小。大部分养殖户都称之为红斑病，这些红色斑点长在猪身上不同部位，红斑病发病率较高，发病时间较长，久治不愈。引起红斑病的原因有以下几点： 　　过敏性皮炎蚊蝇叮咬猪皮肤，猪皮肤的病菌会分泌细胞内毒素，皮肤出现斑块，脂溶性皮炎及毒素性皮炎。 　　免疫抑制类疾病免疫抑制类疾病对猪的危害性很大，猪圆环病毒引起的猪皮炎肾炎综合征，慢性猪瘟和猪附红细胞体等，会直接降低猪的抵抗力，从而增加发病几率。 　　寄生虫及细菌性感染夏季，高温潮湿，蚊虫肆虐，如果猪生活环境卫生不好、饲喂不科学、消毒不彻底、猪会出现大量病菌和寄生虫滋生，猪场的蚊蝇叮咬也会引起猪红斑病发生和各种皮肤性疾病。 　　预防红斑病的出现，要做到以下三点： 　　1、提高猪的免疫力，控制好猪寄生虫问题，症状较为严重的猪，要注射清开灵注射液和头孢噻呋等，可以有效控制猪红斑病。 　　2、加强饲养管理工作，减少应激反应，在夏日天气闷热，仔猪断奶，转栏时候，在饮用水里要加入爱畅，可以减少应激，增加猪的免疫力。 　　3、定时给猪场进行消毒处理，要对猪场进行交替消毒措施，加强蚊虫防护工作，一旦发现病猪时，立即隔离防护。（马雪娟）\",\n" +
                "        \"title\": \"猪身上起红斑点怎么办？\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"cotent\": \"兔子眼睛、鼻梁等处出现白色的痂皮，发痒，不好好吃食，这是什么病？该咋治？ 　　根据描述，判断是兔螨病。 　　症状兔螨病又叫疥螨病，是由寄生于兔体表的痒螨或疥螨引起的一种外寄生性皮肤病。寄生于外耳道的是痒螨，寄生于兔体表的为疥螨。本病的传播性很强，病兔与健康兔直接接触传播，轻者使兔消瘦，影响生产性能，严重者常造成死亡。各种年龄的兔都可发病，但幼兔比成年兔患病严重。 　　防治螨病具有高度的传染性，遗漏一个小的患部，散布少许病料，就有继续蔓延的可能。因此，治疗螨病时一定要认真仔细。 　　1、全面检查治疗前，应详细检查所有病兔，一只不漏，并找出所有患部，便于全面治疗。2、彻底治疗为使药物和虫体充分接触，将患部及其周围3～4厘米处的被毛剪去，用温肥皂水彻底刷洗，除掉硬痂和污物，最后用5%来苏儿液刷洗1次，擦干后涂药。 　　3、重复用药治疗螨病的药物，大多数对螨卵没有杀灭作用，因此，即使患部不大，疗效显著，也必须治疗2～3次（每次间隔5天），以便杀死新孵出的幼虫。 　　4、环境消毒处理病兔的同时，要注意把笼具、用具等彻底消毒（用杀螨剂）。 　　5、防治药物伊维菌素，每公斤体重0.02～0.04毫克，皮下注射，1周后再注射1次，重症者隔1周再注射1次；12.5%双甲脒乳油，可按1：250倍加水稀释成0.05%的水溶液，涂擦患部。也可选用磷丹乳油、溴氰菊酯、螨净、辛硫磷等药物，配成适宜浓度水溶液对患部进行涂擦。因家兔不能药浴，故治疗兔螨病时不宜用药浴。（寇宗彦）\",\n" +
                "        \"title\": \"兔螨病该咋防治\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"cotent\": \"教槽料 使用方法从仔猪出生7天到断奶后14天(体重10千克)左右使用。母猪产后7-14天，每天给仔猪投几颗或少量进行诱食，14天至断奶前投喂4-5次/天，少喂勤添，保证仔猪开食教槽成功，断奶后10-14天逐渐过渡到保育料。 　　注意事项建议补铁和打保健针时可灌喂几粒颗粒料；调成糊状后涂在母猪奶头上，让仔猪吃奶时将料吃下去；按水料4:1的比例调成糊状灌服；先给仔猪补少许颗粒料，稍停一段时间后再吃母乳。教槽诱食时，可采用湿拌料到干料的过渡方法。 　　开封后，要及时扎紧袋口，防止吸潮而变质，并尽快用完，以保证猪群吃到新鲜的教槽料；保持料槽和环境卫生，防止饲料遭受污染；保证仔猪能喝到新鲜、清洁的饮水；少喂勤添，逐渐过渡饲料；注意给仔猪保暖。 　　保育料 　　 使用方法从仔猪断奶后10-14天(体重10千克)到仔猪双月龄(体重25千克)左右使用；从教槽料过渡到保育料时，按7天逐步过渡的方式，并注意少喂勤添，还要适当地控料；如果是品质良好的保育料，建议自由采食，并保证每天有1小时的空槽时间。 　　注意事项保育料若是浓缩料，一定要按厂家建议的配方进行配比；在进行饲料粉碎时，玉米、豆粕务必粉得很细，建议用1.2毫米以下的筛子，以便于营养成分的充分吸收；保证原料的品质，防止使用霉变原料；及时清理料槽，保持环境卫生，防止污染饲料；注意猪群的饮水清洁与保温。 　　小、中、大育肥猪料 　　使用方法小猪料从20-25千克开始使用，直到50千克；中猪料从50千克开始使用直到75千克；大猪料从75千克开始使用直到出栏。按推荐的方案，严格配比，不要随意更改厂家提供的建议配方；从保育猪过渡到小猪料时要经过5-7天的逐渐过渡，并注意适当控料；建议自由采食，并保证每天有1小时的空槽时间；在夏、冬季与出猪前，建议在饲料中添加电解多维或小苏打，以防止猪群应激。 　　注意事项保证原料的品质，严禁使用霉变原料；及时清理料槽，防止污染饲料；猪群密度不要太大，并注意通风换气，还要保持良好的猪舍温、湿度；制定合理的免疫程序，注意猪群的环境卫生与消毒，坚持每星期两次；保证充足的饮水，保持栏舍干燥。（陈浩）\",\n" +
                "        \"title\": \"养猪，饲料选用学问多\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"cotent\": \"俗话说：“3氮6磷8月钾”，现在苹果树到了施钾肥的时候了。钾是果树所需的重要元素之一。特别是对改善苹果品质，提高果品质量有着不可替代的作用。所以钾肥又称“果肥”。 　　补钾的好处：适时适量地施用钾肥，能使果个明显增大，糖分增加，提高着色度；还能使果色鲜艳，果皮光洁，风味口感等内在品质得以改善；抗病虫能力也相应提高，耐贮耐运性增强。 　　选用哪种钾肥：硝酸钾含硝态氮13.5%，含钾46%，是化学中性、生理中性肥料，具有良好的水溶性，长期施用，不会导致土壤酸化。适用于果树幼果膨大期至着色初期，可以促进果肉细胞的膨大。因含有硝态氮，不建议着色后期使用，容易造成返青。 　　磷酸二氢钾含磷52%，含钾约34%,是化学中性，生理中性肥料，具有良好的水溶性。一般在开花前后，可用于促进根系萌发和花芽分化，为开花坐果提供能量；着色期使用可以促进上粉着色、增加果实甜度；果实采摘后使用可以促进枝条老熟，提高果实木质化程度。 　　硫酸钾理论上含钾54%，一般是50%，是化学中性、生理酸性肥料，具有很好的水溶性，但长期使用，会加重土壤酸化，适用于着色后期至果实成熟期，促进果实上粉着色，增加果实甜度。所以根据农作物的生育时期及需肥规律，把化肥用在作物需肥最敏感的时期，根据作物的生育特点，掌握好前轻、中重、后补的原则。 　　什么时候补钾：苹果树对钾的吸收，一般是7月至8月，在这段时间给果树施入适量的钾肥，会收到事半功倍的效果。如在早春或花期前施入同等数量的钾肥，无论是对产量还是对着色度等都不会有明显的效果。 　　补多少合适：施用量可按每百公斤产量施纯钾一公斤为宜。如过量的施入，反而会使果树生理机能遭到破坏，影响对其它元素的吸收利用。苹果树施用钾肥过量则表现出缺镁症，树体内钙的含量也会相对降低。 　　另外，在施用钾肥时，应选用吸收利用率高，效果明显的硫酸钾为宜，其次是氯化钾，也可用草木灰或腐熟的鸡粪等含钾量较高的有机肥于树下撒施，而后浅埋浇水。如用0.3％～0.5％磷酸二氢钾溶液或氨基酸钾300～500倍液进行叶面喷施补充，也可收到较好的效果。（果树种植联盟）\",\n" +
                "        \"title\": \"八月，苹果园补钾正当时\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"cotent\": \"抹芽抹去位置不当或过密的芽，包括根蘖、主干上发出的隐芽、结果母枝上萌发的双芽、三芽及结果母枝上的多余芽。抹芽一般从芽萌动期开始，大约两周左右抹一次，抹芽应及时、彻底，减少树体营养的无效消耗。 　　疏枝秋季疏枝主要是疏除由基部萌发的所有徒长枝，还要适当疏除过密枝、衰弱枝、机械性损伤枝及临时性母枝上萌发的部分结果枝。秋季疏枝宜早，一般在立秋之后进行，保证猕猴桃架面通风透光效果。 　　绑蔓绑蔓是猕猴桃园管理的重要一环。当新梢长到39～40厘米时应开始绑蔓，每隔两周进行一次；调正新梢生长方向，避免互相交叉、重叠，使其在架面上分布均匀，从中心铁丝引向第2、3道铁丝上固定。为了防止枝条与铁丝摩擦受损，绑蔓时先将细绳在铁丝上缠绕1～2圈，再绑缚枝条，不能将枝条和铁丝绑在一起，也不能将枝条绑的太紧，要留有余地，以免影响加粗生长。 　　摘心从主蔓或结果母枝基部发出的徒长枝，如位置适宜，可留2～3芽短截，使其重新发出二次枝，生长势头缓和，可培养成结果枝的预备枝；对外围计划冬季修剪的结果枝，可在结果部位以上留6～8片叶子进行摘心，对发出的二次枝应及时抹除，这样既可节约树体养分，又可保证果实的正常生长；对计划留作下年结果母枝的枝条，一般情况下不要急于摘心，当顶端开始弯曲缠绕时，摘去3～5厘米，使其停止生长，促使枝芽成熟，发出的二次枝，当顶部开始缠绕时，再次摘心。（农科）\",\n" +
                "        \"title\": \"猕猴桃秋季修剪做好四点\"\n" +
                "    }]";
        Gson gson = new Gson();
        mData = gson.fromJson(json, new TypeToken<ArrayList<NewsBean1>>() {
        }.getType());

        NewsTitlesView newsTitlesView = new NewsTitlesView(mData, fManager);
        FragmentTransaction ft = fManager.beginTransaction();
//        ft.hide(newsTitlesView);
        ft.addToBackStack(null);
        ft.replace(R.id.fl_content, newsTitlesView);
        ft.commit();
    }

    private void initView() {
        FrameLayout frameLayout = findViewById(R.id.fl_content);
    }

//    @Override
//    public void onBackPressed() {
//        if (fManager.getBackStackEntryCount() == 0) {
//            if ((System.currentTimeMillis() - exitTime) > 2000) {
//                Toast.makeText(getApplicationContext(), "再按一次退出程序",
//                        Toast.LENGTH_SHORT).show();
//                exitTime = System.currentTimeMillis();
//            } else {
//                super.onBackPressed();
//            }
//        } else {
//            fManager.popBackStack();
////            .setText("新闻列表");
//        }
//    }
}
