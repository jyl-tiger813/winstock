﻿/*
 * GetPy.java
 */
package jyl.util;

import java.io.UnsupportedEncodingException;

/**
 * 取得汉字拼音码.
 * 支持GBK大字符集.
 * @author Zhao Honghui
 * @version 1.0
 */
public class GetPy {

	  private static final String GB_2312 = 
	      "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbp" +
	      "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbpbbbbbbbbbbbbbbbbbb" +
	      "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
	      "pbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
	      "bbbbbbbbbbbbbbbbbbbbcccccccccccccccccccccccccccccc" +
	      "ccccccccccccccccccccccccccccccccccczcccccccccccccc" +
	      "ccccccccccccccccccccccccccccccccccccsccccccccccccc" +
	      "cccccccccccccccccccccccccccccccccccccccccczccccccc" +
	      "cccccccccccccccccccccccccccccccccccccccccccccccccc" +
	      "cccddddddddddddddddddddddddddddddddddddddddddddddd" +
	      "dddddddddddddddddddddzdddddddddddddddddddddddddddd" +
	      "dddddddddddddddddddddddddddddddtdddddddddddddddddd" +
	      "dddddddddddddddddddddddddddddddddddddeeeeeeeeeeeee" +
	      "eeeeeeeeefffffffffffffffffffffffffffffffffffffffff" +
	      "ffffffffffffffffffffffffffffffffffffffffffffffffff" +
	      "fffffffffffffpffffffffffffffffffffgggggggggggggggg" +
	      "ggggggggggggggggggghggggggggggggghgggggggggggggggg" +
	      "gggggggggggggggggggggggggggggggggggggggggggggggggg" +
	      "ggggggggggggggggggggggggggggggggggggggghhhhhhhhhhh" +
	      "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhmhhhhhhhhhhh" +
	      "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh" +
	      "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh" +
	      "hhhhhhhhhhhhhhhhhhhhjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj" +
	      "jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj" +
	      "jjjjjjjjjjjjjjkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj" +
	      "jjjjjjjyjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj" +
	      "jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj" +
	      "jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj" +
	      "jjjjjjjjjjjjjjjkkkgkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkh" +
	      "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" +
	      "kkkkkkkkkkkkkkklllllllllllllllllllllllllllllllllll" +
	      "llllllllllllllllllllllllllllllllllllllllllllllllll" +
	      "llllllllllllllllllllllllllllllllllllllllllllllllll" +
	      "llllllllllllllllllllllllllllllllllllllllllllllllll" +
	      "llllllllllllllllllllllllllllllllllllllllllllllllll" +
	      "lllllllllllllmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm" +
	      "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm" +
	      "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm" +
	      "mmmmmmmmmmmmmmnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn" +
	      "nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnooooo" +
	      "oooppppppppppppppppppppppppppppppppppppppppppppppp" +
	      "pppppppppppppppppppppppppppppppppppppppppppppppppp" +
	      "ppppppppppppppppppppppppbqqqqqqqqqqqqqqqqqqqqqqqqq" +
	      "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq" +
	      "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq" +
	      "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqrrrrrrrrrrrrrrrrrr" +
	      "rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrsssssssss" +
	      "ssssssssssssssssssssssssssssssssssssssssssssssssss" +
	      "ssssssssssssssssssssssssssssssssssssssssssssssssss" +
	      "ssssssssssssssssssssssssssssssssssssssssssssssssss" +
	      "ssssssssssssssssssssssssssssssssssssssssssssssssss" +
	      "sssssssssssssssssssssssssssssssssssssssssssssssssx" +
	      "sssssssssssssssssssssssssssttttttttttttttttttttttt" +
	      "tttttttttttttttttttttttttttttttttttttttttttttttttt" +
	      "tttttttttttttttttttttttttttttttttttttttttttttttttt" +
	      "tttttttttttttttttttttttttttttttttwwwwwwwwwwwwwwwww" +
	      "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww" +
	      "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww" +
	      "wwwxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxsx" +
	      "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
	      "xxxxxxxxxxxxxxxxxxxxxjxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +
	      "xxxxxhxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxcxxxxxxxxx" +
	      "xxxxxxxxxxxxxxxxxxxxxxxxxxyyyyyyyyyyyyyyyyyyyyyyyy" +
	      "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy" +
	      "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy" +
	      "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy" +
	      "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy" +
	      "yyyyyyyyyyyyyyyyyyyyyyyyxyyyyyyyyyyyyyyyyyyyyyyyyy" +
	      "yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyzzzzzzzzzzzzzzzzzz" +
	      "zzzzzzzzzzzzzzzzzzzzzczzzzzzzzzzzzzzzzzzzzzzzzzzzz" +
	      "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz" +
	      "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz" +
	      "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz" +
	      "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz" +
	      "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz" +
	      "zzzzz     cjwgnspgcgnesypbtyyzdxykygtdjnnjqmbsjzsc" +
	      "yjsyyfpgkbzgylywjkgkljywkpjqhytwddzlsymrypywwcckzn" +
	      "kyygttngjnykkzytcjnmcylqlypysfqrpzslwbtgkjfyxjwzlt" +
	      "bncxjjjjtxdttsqzycdxxhgckbphffsswybgmxlpbylllhlxst" +
	      "zmyjhsojnghdzqyklgjhsgqzhxqgkxzzwyscscjxyeyxadzpmd" +
	      "ssmzjzqjyzcdjzwqjbyzbjgznzcpwhwxhqkmwfbpbydtjzzkxx" +
	      "ylygxfptyjyyzpszlfchmqshgmxxsxjyqdcsbbqbefsjyhxwgz" +
	      "kpylqbgldlcdtnmaeddkssngycsgxlyzaypnptsdkdylhgymyl" +
	      "cxpycjndqjwxqxfyyfjlejpzrxccqwqqsbzkymgplbmjrqcfln" +
	      "ymyqmtqyrbcjthztqfrxqhxmqjcjlyxgjmshzkbswyemyltxfs" +
	      "ydsglycjqxsjnqbsctyhbftdcyjdjyyghqfsxwckqkxebptlpx" +
	      "jzsrmebwhjlpjslyysmdxlclqkxlhxjrzjmfqhxhwywsbhtrxx" +
	      "glhqhfnmgykldyxzpylggtmtcfpnjjzyljtyanjgbjplqgszyq" +
	      "yaxbkysecjsznslyzhzxlzcghpxzhznytdsbcjkdlzayfmytle" +
	      "bbgqyzkggldndnyskjshdlyxbcgyxypkdjmmzngmmclgezszxz" +
	      "jfznmlzzthcsydbdllscddnlkjykjsycjlkwhqasdknhcsgaeh" +
	      "daashtcplcpqybsdmpjlpzjoqlcdhjxysprchnwjnlhlyyqyhw" +
	      "zptczgwwmzffjqqqqyxaclbhkdjxdgmmydqxzllsygxgkjrywz" +
	      "wyclzmssjzldbydcpcxyhlxchyzjqsfqagmnyxpfrkssbjlyxy" +
	      "syglnscmhcwwmnzjjlxxhchsyzsttxrycyxbyhcsmxjsznpwgp" +
	      "xxtaybgajcxlysdccwzocwkccsbnhcpdyznfcyytyckxkybsqk" +
	      "kytqqxfcwchcykelzqbsqyjqcclmthsywhmktlkjlycxwheqqh" +
	      "tqkjpqsqscfymmdmgbwhwlgsllystlmlxpthmjhwljzyhzjxht" +
	      "xjlhxrswlwzjcbxmhzqxsdzpsgfcsglsxymqshxpjxwmyqksmy" +
	      "plrthbxftpmhyxlchlhlzylxgsssstclsldclrpbhzhxyyfhbb" +
	      "gdmycnqqwlqhjjzywjzyejjdhpblqxtqkwhlchqxagtlxljxms" +
	      "ljhtzkzjecxjcjnmfbycsfywybjzgnysdzsqyrsljpclpwxsdw" +
	      "ejbjcbcnaytwgmpapclyqpclzxsbnmsggfnzjjbzsfzyndxhpl" +
	      "qkzczwalsbccjxjyzgwkypsgxfzfcdkhjgxtlqfsgdslqwzkxt" +
	      "mhsbgzmjzrglyjbpmlmsxlzjqzhzyjczydjwfmjklddpmjegxy" +
	      "hylxhlqyqhkycwcjmyyxnatjhyccxzpcqlbzwwytwsqcmlpmyr" +
	      "jcccxfpznzzljplxxyztzlgdltcklyrzzgqtkjhhgjljaxfgfj" +
	      "zslcfdqzlclgjdjcsnzlljpjqdcclcjxmyzftsxgcgsbrzxjqq" +
	      "ctzhgyqtjqqlzxjylylncyamcstylpdjbyregklzyzhlyszqlz" +
	      "nwczcllwjqjjjkdgjzolbbzppglghtgzxyjhzmycnqsycyhbhg" +
	      "xkamtxyxnbskyzzgjzlqjtfcjxdygjqjjpmgwgjjjpkqsbgbmm" +
	      "cjssclpqpdxcdyykyfcjddyygywrhjrtgznyqldkljszzgzqzj" +
	      "gdykshpzmtlcpwnjyfyzdjcnmwescyglbtzcgmssllyxqsxxbs" +
	      "jsbbsgghfjlypmzjnlyywdqshzxtyywhmcyhywdbxbtlmsyyyf" +
	      "sxjchtxxlhjhfssxzqhfzmzcztqcxzxrttdjhnnyzqqmtqdmmz" +
	      " ytxmjgdxcdyzbffallztdltfxmxqzdngwqdbdczjdxbzgsqqd" +
	      "djcmbkzffxmkdmdsyyszcmljdsynsprskmkmpcklgdbqtfzswt" +
	      "fgglyplljzhgjjgypzltcsmcnbtjbqfkdhpyzgkpbbymtdssxt" +
	      "bnpdkleycjnyddykzddhqhsdzsctarlltkzlgecllkjlqjaqnb" +
	      "dkkghpjxzqksecshalqfmmgjnlyjbbtmlyzxdxjpldlpcqdhzy" +
	      "cbzsczbzmsljflkrzjsnfrgjhxpdhyjybzgdlqcsezgxlblhyx" +
	      "twmabchecmwyjyzlljjyhlgbdjlslygkdzpzxjyyzlwcxszfgw" +
	      "yydlyhcljscmbjhblyzlycblydpdqysxqzbytdkyxlyycnrjmp" +
	      "dqgklcljbcxbjddbblblczqrppxjcjlzcshltoljnmdddlngka" +
	      "thqhjhykheznmshrphqqjchgmfprxhjgdychgklyrzqlcyqjnz" +
	      "sqtkqjymszxwlcfqqqxyfggyptqwlmcrnfkkfsyylybmqammmy" +
	      "xctpshcptxxzzsmphpshmclmldqfyqxszyjdjjzzhqpdszglst" +
	      "jbckbxyqzjsgpsxqzqzrqtbdkwxzkhhgflbcsmdldgdzdblzyy" +
	      "cxnncsybzbfglzzxswmsccmqnjqsbdqsjtxxmbltxcclzshzcx" +
	      "rqjgjylxzfjphymzqqydfqjqlzznzjcdgzygztxmzysctlkpht" +
	      "xhtlbjxjlxscdqxcbbtjfqzfsltjbtkqbxxjjljchczdbzjdcz" +
	      "jdcprnpqcjpfczlclzxzdmxmphjsgzgszzqlylwtjpfsyaxmcj" +
	      "btzyycwmytzsjjlqcqlwzmalbxyfbpnlsfhtgjwejjxxglljst" +
	      "gshjqlzfkcgnndszfdeqfhbsaqtgylbxmmygszldydqmjjrgbj" +
	      "tkgdhgkblqkbdmbylxwcxyttybkmrtjzxqjbhlmhmjjzmqasld" +
	      "cyxyqdlqcafywyxqhz";

	  private static final String GBK_3 =
	      "ksxsm sdqlybjjjgczbjfya jhphsyzgj   sn      xy  ng" +
	      "    lggllyjds yssgyqyd xjyydldwjjwbbftbxthhbczcrfm" +
	      "qwyfcwdzpyddwyxjajpsfnzyjxxxcxnnxxzzbpysyzhmzbqbzc" +
	      "ycbxqsbhhxgfmbhhgqcxsthlygymxalelccxzrcsd njjtzzcl" +
	      "jdtstbnxtyxsgkwyflhjqspxmxxdc lshxjbcfybyxhczbjyzl" +
	      "wlcz gtsmtzxpqglsjfzzlslhdzbwjncjysnycqrzcwybtyftw" +
	      "ecskdcbxhyzqyyxzcffzmjyxxsdcztbzjwszsxyrnygmdthjxs" +
	      "qqccsbxrytsyfbjzgclyzzbszyzqscjhzqydxlbpjllmqxtydz" +
	      "sqjtzplcgqtzwjbhcjdyfxjelbgxxmyjjqfzasyjnsydk jcjs" +
	      "zcbatdclnjqmwnqncllkbybzzsyhjqltwlccxthllzntylnzxd" +
	      "dtcenjyskkfksdkghwnlsjt jymrymzjgjmzgxykymsmjklfxm" +
	      "tghpfmqjsmtgjqdgyalcmzcsdjlxdffjc f  ffkgpkhrcjqcj" +
	      "dwjlfqdmlzbjjscgckdejcjdlzyckscclfcq czgpdqzjj hdd" +
	      "wgsjdkccctllpskghzzljlgjgjjtjjjzczmlzyjkxzyzmljkyw" +
	      "xmkjlkjgmclykjqlblkmdxwyxysllpsjqjqxyqfjtjdmxxllcr" +
	      "qyjb xgg pjygegdjgnjyjkhqfqzkhyghdgllsdjjxkyoxnzsx" +
	      "wwxdcskxxjyqscsqkjexsyzhydz ptqyzmtstzfsyldqagylcq" +
	      "lyyyhlrq ldhsssadsjbrszxsjyrcgqc hmmxzdyohycqgphhy" +
	      "nxrhgjlgwqwjhcstwasjpmmrdsztxyqpzxyhyqxtpbfyhhdwzb" +
	      "txhqeexzxxkstexgltxydn  hyktmzhxlplbmlsfhyyggbhyqt" +
	      "xwlqczydqdq gd lls zwjqwqajnytlxanzdecxzwwsgqqdyzt" +
	      "chyqzlxygzglydqtjtadyzzcwyzymhyhyjzwsxhzylyskqysbc" +
	      "yw  xjzgtyxqsyhxmchrwjpwxzlwjs sgnqbalzzmtjcjktsax" +
	      "ljhhgoxzcpdmhgtysjxhmrlxjkxhmqxctxwzbkhzccdytxqhlx" +
	      "hyx syydz znhxqyaygypdhdd pyzndltwxydpzjjcxmtlhbyn" +
	      "yymhzllhnmylllmdcppxmxdkycydltxchhznaclcclylzsxzjn" +
	      "zln lhyntkyjpychegttgqrgtgyhhlgcwyqkpyyyttttlhylly" +
	      "ttplkyzqqzdq  nmjzxyqmktfbjdjjdxbtqzgtsyflqgxblzfh" +
	      " zadpmjhlccyhdzfgydgcyxs hd d axxbpbyyaxcqffqyjxdl" +
	      "jjzl bjydyqszwjlzkcdtctbkdyzdqjnkknjgyeglfykasntch" +
	      "blwzbymjnygzyheyfjmctyfzjjhgck lxhdwxxjkyykssmwctq" +
	      "zlpbzdtwzxzag kwxl lspbclloqmmzslbczzkdcz xgqqdcyt" +
	      "zqwzqssfpktfqdcdshdtdwfhtdy jaqqkybdjyxtlj drqxxxa" +
	      "ydrjlklytwhllrllcxylbw z  zzhkhxksmdsyyjpzbsqlcxxn" +
	      "xwmdq gqmmczjgttybhyjbetpjxdqhkzbhfdxkawtwajldyjsf" +
	      "hblddqjncxfjhdfjjwzpkzypcyzynxff ydbzznytxzembsehx" +
	      "fzmbflzrsymzjrdjgxhjgjjnzzxhgxhymlpeyyxtgqshxssxmf" +
	      "mkcctxnypszhzptxwywxyysljsqxzdleelmcpjclxsqhfwwtff" +
	      "tnqjjjdxhwlyznflnkyyjldx hdynrjtywtrmdrqhwqcmfjdyz" +
	      "hmyyxjwzqtxtlmrspwwchjb xygcyyrrlmpymkszyjrmysntpl" +
	      "nbpyyxmykyngjzznlzhhanmpgwjdzmxxmllhgdzxyhxkrycjmf" +
	      "fxyhjfssqlxxndyca nmtcjcyprrnytyqym sxndlylyljnlxy" +
	      "shqmllyzljzxstyzsmcqynzlxbnnylrqtryyjzzhsytxcqgxzs" +
	      "shmkczyqhzjnbh qsnjnzybknlqhznswxkhjyybqlbfl p bkq" +
	      "zxsddjmessmlxxkwnmwwwydkzggtggxbjtdszxnxwmlptfxlcx" +
	      "jjljzxnwxlyhhlrwhsc ybyawjjcwqqjzzyjgxpltzftpakqpt" +
	      "lc  xtx hklefdleegqymsawhmljtwyqlyjeybqfnlyxrdsctg" +
	      "gxyyn kyqctlhjlmkkcgygllldzydhzwpjzkdyzzhyyfqytyzs" +
	      "ezzlymhjhtwyzlkyywzcskqqtdxwctyjklwqbdqyncs szjlkc" +
	      "dcdtlzzacqqzzddxyplxzbqjylzllqdzqgyjyjsyxnyyynyjxk" +
	      "xdazwrdljyyynjlxllhxjcykynqcclddnyyykyhhjcl pb qzz" +
	      "yjxj fzdnfpzhddwfmyypqjrssqzsqdgpzjwdsjdhzxwybp gp" +
	      "tmjthzsbgzmbjczwbbzmqcfmbdmcjxljbgjtz mqdyxjzyctyz" +
	      "tzxtgkmybbcljssqymscx jeglxszbqjjlyxlyctsxmcwfa kb" +
	      "qllljyxtyltxdphnhfqyzyes sdhwdjbsztfd czyqsyjdzjqp" +
	      "bs j fbkjbxtkqhmkwjjlhhyyyyywyycdypczyjzwdlfwxwzzj" +
	      "cxcdjzczlxjjtxbfwpxzptdzbccyhmlxbqlrtgrhqtlf mwwjx" +
	      "jwcysctzqhxwxkjybmpkbnzhqcdtyfxbyxcbhxpsxt m sxlhk" +
	      "mzxydhwxxshqhcyxglcsqypdh my ypyyykzljqtbqxmyhcwll" +
	      "cyl ewcdcmlggqktlxkgndgzyjjlyhqdtnchxwszjydnytcqcb" +
	      "hztbxwgwbxhmyqsycmqkaqyncs qhysqyshjgjcnxkzycxsbxx" +
	      "hyylstyxtymgcpmgcccccmztasgqzjlosqylstmqsqdzljqqyp" +
	      "lcycztcqqpbqjclpkhz yyxxdtddsjcxffllxmlwcjcxtspyxn" +
	      "dtjsjwxqqjskyylsjhaykxcyydmamdqmlmczncybzkkyflmcsc" +
	      "lhxrcjjgslnmtjzzygjddzjzk qgjyyxzxxqhheytmdsyyyqlf" +
	      " zzdywhscyqwdrxqjyazzzdywbjwhyqszywnp  azjbznbyzzy" +
	      "hnscpjmqcy zpnqtbzjkqqhngccxchbzkddnzhjdrlzlsjljyx" +
	      "ytbgtcsqmnjpjsrxcfjqhtpzsyjwbzzzlstbwwqsmmfdwjyzct" +
	      "bwzwqcslqgdhqsqlyzlgyxydcbtzkpj gm pnjkyjynhpwsnsz" +
	      "zxybyhyzjqjtllcjthgdxxqcbywbwzggqrqzssnpkydznxqxjm" +
	      "y dstzplthzwxwqtzenqzw ksscsjccgptcslccgllzxczqthn" +
	      "jgyqznmckcstjskbjygqjpldxrgzyxcxhgdnlzwjjctsbcjxbf" +
	      "zzpqdhjtywjynlzzpcjdsqjkdxyajyemmjtdljyryynhjbngzj" +
	      "kmjxltbsllrzylcscnxjllhyllqqqlxymswcxsljmc zlnsdwt" +
	      "jllggjxkyhbpdkmmscsgxjcsdybxdndqykjjtxdygmzzdzslo " +
	      "yjsjzdlbtxxxqqjzlbylwsjjyjtdzqqzzzzjlzcdzjhpl qplf" +
	      "fjzysj zfpfzksyjjhxttdxcysmmzcwbbjshfjxfqhyzfsjybx" +
	      "pzlhmbxhzxfywdab lktshxkxjjzthgxh jxkzxszzwhwtzzzs" +
	      "nxqzyawlcwxfxyyhxmyyswqmnlycyspjkhwcqhyljmzxhmcnzh" +
	      "hxcltjplxyjhdyylttxfszhyxxsjbjyayrmlckd yhlrlllsty" +
	      "zyyhscszqxkyqfpflk ntljmmtqyzwtlll s rbdmlqjbcc qy" +
	      "wxfzrzdmcyggzjm  mxyfdxc shxncsyjjmpafyfnhyzxyezy " +
	      "sdl zztxgfmyyysnbdnlhpfzdcyfssssn zzdgpafbdbzszbsg" +
	      "cyjlm  z yxqcyxzlckbrbrbzcycjzeeyfgzlyzsfrtkqsxdcm" +
	      "z  jl xscbykjbbrxllfqwjhyqylpzdxczybdhzrbjhwnjtjxl" +
	      "kcfssdqyjkzcwjl b  tzlltlqblcqqccdfpphczlyygjdgwcf" +
	      "czqyyyqyrqzslszfcqnwlhjcjjczkypzzbpdc   jgx gdz  f" +
	      "gpsysdfwwjzjyxyyjyhwpbygxrylybhkjksftzmmkhtyysyyzp" +
	      "yqydywmtjjrhl   tw  bjycfnmgjtysyzmsjyjhhqmyrszwtr" +
	      "tzsskx gqgsptgcznjjcxmxgzt ydjz lsdglhyqgggthszpyj" +
	      "hhgnygkggmdzylczlxqstgzslllmlcskbljzzsmmytpzsqjcj " +
	      " zxzzcpshkzsxcdfmwrllqxrfzlysdctmxjthjntnrtzfqyhqg" +
	      "llg   sjdjj tqjlnyhszxcgjzypfhdjspcczhjjjzjqdyb ss" +
	      "lyttmqtbhjqnnygjyrqyqmzgcjkpd gmyzhqllsllclmholzgd" +
	      "yyfzsljc zlylzqjeshnylljxgjxlyjyyyxnbzljsszcqqzjyl" +
	      "lzldj llzllbnyl hxxccqkyjxxxklkseccqkkkcgyyxywtqoh" +
	      "thxpyxx hcyeychbbjqcs szs lzylgezwmysx jqqsqyyycmd" +
	      "zywctjsycjkcddjlbdjjzqysqqxxhqjohdyxgmajpchcpljsmt" +
	      "xerxjqd pjdbsmsstktssmmtrzszmldj rn sqxqydyyzbdsln" +
	      "fgpzmdycwfdtmypqwytjzzqjjrjhqbhzpjhnxxyydyhhnmfcpb" +
	      "zpzzlzfmztzmyftskyjyjzhbzzygh pzcscsjssxfjgdyzyhzc" +
	      "whcsexfqzywklytmlymqpxxskqjpxzhmhqyjs cjlqwhmybdhy" +
	      "ylhlglcfytlxcjscpjskphjrtxteylssls yhxscznwtdwjslh" +
	      "tqdjhgydphcqfzljlzptynlmjllqyshhylqqzypbywrfy js y" +
	      "p yrhjnqtfwtwrchygmm yyhsmzhngcelqqmtcwcmpxjjfyysx" +
	      "ztybmstsyjdtjqtlhynpyqzlcxznzmylflwby jgsylymzctdw" +
	      "gszslmwzwwqzsayysssapxwcmgxhxdzyjgsjhygscyyxhbbzjk" +
	      "ssmalxycfygmqyjycxjlljgczgqjcczotyxmtthlwtgfzkpzcx" +
	      "kjycxctjcyh xsgckxzpsjpxhjwpjgsqxxsdmrszzyzwsykyzs" +
	      "hbcsplwsscjhjlchhylhfhhxjsx lnylsdhzxysxlwzyhcldyh" +
	      "zmdyspjtqznwqpsswctst zlmssmnyymjqjzwtyydchqlxkwbg" +
	      "qybkfc jdlzllyylszydwhxpsbcmljscgbhxlqrljxysdwxzsl" +
	      "df hlslymjljylyjcdrjlfsyjfnllcqyqfjy szlylmstdjcyh" +
	      "zllnwlxxygyygxxhhzzxczqzfnwpypkpypmlgxgg dxzzkzfbx" +
	      "xlzptytswhzyxhqhxxxywzyswdmzkxhzphgchj lfjxptzthly" +
	      "xcrhxshxkjxxzqdcqyl jlkhtxcwhjfwcfpqryqxyqy gpggsc" +
	      "sxngkchkzxhflxjbyzwtsxxncyjjmwzjqrhfqsyljzgynslgtc" +
	      "ybyxxwyhhxynsqymlywgyqbbzljlpsytjzhyzwlrorjkczjxxy" +
	      "xchdyxyxxjddsqfxyltsfxlmtyjmjjyyxltcxqzqhzlyyxzh n" +
	      "lrhxjcdyhlbrlmrllaxksllljlxxxlycry lccgjcmtlzllyzz" +
	      "pcw jyzeckzdqyqpcjcyzmbbcydcnltrmfgyqbsygmdqqzmkql" +
	      "pgtbqcjfkjcxbljmswmdt  ldlppbxcwkcbjczhkphyyhzkzmp" +
	      "jysylpnyyxdb";

	  private static final String GBK_4 =
	      "kxxmzjxsttdzxxbzyshjpfxpqbyljqkyzzzwl zgfwyctjxjpy" +
	      "yspmsmydyshqy zchmjmcagcfbbhplxtyqx djgxdhkxxnbhrm" +
	      "lnjsltsmrnlxqjyzlsqglbhdcgyqyyhwfjybbyjyjjdpqyapfx" +
	      "cgjscrssyz lbzjjjlgxzyxyxsqkxbxxgcxpld wetdwwcjmbt" +
	      "xchxyxxfxllj fwdpzsmylmwytcbcecblgdbqzqfjdjhymcxtx" +
	      "drmjwrh xcjzylqdyhlsrsywwzjymtllltqcjzbtckzcyqjzqa" +
	      "lmyhwwdxzxqdllqsgjfjljhjazdjgtkhsstcyjfpszlxzxrwgl" +
	      "dlzr lzqtgslllllyxxqgdzybphl x bpfd   hy jcc dmzpp" +
	      "z cyqxldozlwdwyythcqsccrsslfzfp qmbjxlmyfgjb m jwd" +
	      "n mmjtgbdzlp hsymjyl hdzjcctlcl ljcpddqdsznbgzxxcx" +
	      "qycbzxzfzfjsnttjyhtcmjxtmxspdsypzgmljtycbmdkycsz z" +
	      "yfyctgwhkyjxgyclndzscyzssdllqflqllxfdyhxggnywyllsd" +
	      "lbbjcyjzmlhl xyyytdlllb b bqjzmpclmjpgehbcqax hhhz" +
	      "chxyhjaxhlphjgpqqzgjjzzgzdqybzhhbwyffqdlzljxjpalxz" +
	      "daglgwqyxxxfmmsypfmxsyzyshdzkxsmmzzsdnzcfp ltzdnmx" +
	      "zymzmmxhhczjemxxksthwlsqlzllsjphlgzyhmxxhgzcjmhxtx" +
	      "fwkmwkdthmfzzydkmsclcmghsxpslcxyxmkxyah jzmcsnxyym" +
	      "mpmlgxmhlmlqmxtkzqyszjshyzjzybdqzwzqkdjlfmekzjpezs" +
	      "wjmzyltemznplplbpykkqzkeqlwayyplhhaq jkqclhyxxmlyc" +
	      "cyskg  lcnszkyzkcqzqljpmzhxlywqlnrydtykwszdxddntqd" +
	      "fqqmgseltthpwtxxlwydlzyzcqqpllkcc ylbqqczcljslzjxd" +
	      "dbzqdljxzqjyzqkzljcyqdypp pqykjyrpcbymxkllzllfqpyl" +
	      "llmsglcyrytmxyzfdzrysyztfmsmcl ywzgxzggsjsgkdtggzl" +
	      "ldzbzhyyzhzywxyzymsdbzyjgtsmtfxqyjssdgslnndlyzzlrx" +
	      "trznzxnqfmyzjzykbpnlypblnzz jhtzkgyzzrdznfgxskgjtt" +
	      "yllgzzbjzklplzylxyxbjfpnjzzxcdxzyxzggrs jksmzjlsjy" +
	      "wq yhqjxpjzt lsnshrnypzt wchklpszlcyysjylybbwzpdwg" +
	      "cyxckdzxsgzwwyqyytctdllxwkczkkcclgcqqdzlqcsfqchqhs" +
	      "fmqzlnbbshzdysjqplzcd cwjkjlpcmz jsqyzyhcpydsdzngq" +
	      "mbsflnffgfsm q lgqcyybkjsrjhzldcftlljgjhtxzcszztjg" +
	      "gkyoxblzppgtgyjdhz zzllqfzgqjzczbxbsxpxhyyclwdqjjx" +
	      "mfdfzhqqmqg yhtycrznqxgpdzcszcljbhbzcyzzppyzzsgyhc" +
	      "kpzjljnsc sllxb mstldfjmkdjslxlsz p pgjllydszgql l" +
	      "kyyhzttnt  tzzbsz ztlljtyyll llqyzqlbdzlslyyzyfszs" +
	      "nhnc   bbwsk rbc zm  gjmzlshtslzbl q xflyljqbzg st" +
	      "bmzjlxfnb xjztsfjmssnxlkbhsjxtnlzdntljjgzjyjczxygy" +
	      "hwrwqnztn fjszpzshzjfyrdjfcjzbfzqchzxfxsbzqlzsgyft" +
	      "zdcszxzjbqmszkjrhyjzckmjkhchgtxkjqalxbxfjtrtylxjhd" +
	      "tsjx j jjzmzlcqsbtxhqgxtxxhxftsdkfjhzxjfj  zcdlllt" +
	      "qsqzqwqxswtwgwbccgzllqzbclmqqtzhzxzxljfrmyzflxys x" +
	      "xjk xrmqdzdmmyxbsqbhgcmwfwtgmxlzpyytgzyccddyzxs g " +
	      "yjyznbgpzjcqswxcjrtfycgrhztxszzt cbfclsyxzlzqmzlmp" +
	      " lxzjxslbysmqhxxz rxsqzzzsslyflczjrcrxhhzxq dshjsj" +
	      "jhqcxjbcynsssrjbqlpxqpymlxzkyxlxcjlcycxxzzlxlll hr" +
	      "zzdxytyxcxff bpxdgygztcqwyltlswwsgzjmmgtjfsgzyafsm" +
	      "lpfcwbjcljmzlpjjlmdyyyfbygyzgyzyrqqhxy kxygy fsfsl" +
	      "nqhcfhccfxblplzyxxxkhhxshjzscxczwhhhplqalpqahxdlgg" +
	      "gdrndtpyqjjcljzljlhyhyqydhz zczywteyzxhsl jbdgwxpc" +
	      "  tjckllwkllcsstknzdnqnttlzsszyqkcgbhcrrychfpfyrwq" +
	      "pxxkdbbbqtzkznpcfxmqkcypzxehzkctcmxxmx nwwxjyhlstm" +
	      "csqdjcxctcnd p lccjlsblplqcdnndscjdpgwmrzclodansyz" +
	      "rdwjjdbcxwstszyljpxloclgpcjfzljyl c cnlckxtpzjwcyx" +
	      "wfzdknjcjlltqcbxnw xbxklylhzlqzllzxwjljjjgcmngjdzx" +
	      "txcxyxjjxsjtstp ghtxdfptffllxqpk fzflylybqjhzbmddb" +
	      "cycld tddqlyjjwqllcsjpyyclttjpycmgyxzhsztwqwrfzhjg" +
	      "azmrhcyy ptdlybyznbbxyxhzddnh msgbwfzzjcyxllrzcyxz" +
	      "lwjgcggnycpmzqzhfgtcjeaqcpjcs dczdwldfrypysccwbxgz" +
	      "mzztqscpxxjcjychcjwsnxxwjn mt mcdqdcllwnk zgglcczm" +
	      "lbqjqdsjzzghqywbzjlttdhhcchflsjyscgc zjbypbpdqkxwy" +
	      "yflxncwcxbmaykkjwzzzrxy yqjfljphhhytzqmhsgzqwbwjdy" +
	      "sqzxslzyymyszg x hysyscsyznlqyljxcxtlwdqzpcycyppnx" +
	      "fyrcmsmslxglgctlxzgz g tc dsllyxmtzalcpxjtjwtcyyjb" +
	      "lbzlqmylxpghdlssdhbdcsxhamlzpjmcnhjysygchskqmc lwj" +
	      "xsmocdrlyqzhjmyby lyetfjfrfksyxftwdsxxlysjslyxsnxy" +
	      "yxhahhjzxwmljcsqlkydztzsxfdxgzjksxybdpwnzwpczczeny" +
	      "cxqfjykbdmljqq lxslyxxylljdzbsmhpsttqqwlhogyblzzal" +
	      "xqlzerrqlstmypyxjjxqsjpbryxyjlxyqylthylymlkljt llh" +
	      "fzwkhljlhlj klj tlqxylmbtxchxcfxlhhhjbyzzkbxsdqc j" +
	      "zsyhzxfebcqwyyjqtzyqhqqzmwffhfrbntpcjlfzgppxdbbztg" +
	      " gchmfly xlxpqsywmngqlxjqjtcbhxspxlbyyjddhsjqyjxll" +
	      "dtkhhbfwdysqrnwldebzwcydljtmxmjsxyrwfymwrxxysztzzt" +
	      "ymldq xlyq jtscxwlprjwxhyphydnxhgmywytzcs tsdlwdcq" +
	      "pyclqyjwxwzzmylclmxcmzsqtzpjqblgxjzfljjytjnxmcxs c" +
	      "dl dyjdqcxsqyclzxzzxmxqrjhzjphfljlmlqnldxzlllfypny" +
	      "ysxcqqcmjzzhnpzmekmxkyqlxstxxhwdcwdzgyyfpjzdyzjzx " +
	      "rzjchrtlpyzbsjhxzypbdfgzzrytngxcqy b cckrjjbjerzgy" +
	      "  xknsjkljsjzljybzsqlbcktylccclpfyadzyqgk tsfc xdk" +
	      "dyxyfttyh  wtghrynjsbsnyjhkllslydxxwbcjsbbpjzjcjdz" +
	      "bfxxbrjlaygcsndcdszblpz dwsbxbcllxxlzdjzsjy lyxfff" +
	      "bhjjxgbygjpmmmpssdzjmtlyzjxswxtyledqpjmygqzjgdblqj" +
	      "wjqllsdgytqjczcjdzxqgsgjhqxnqlzbxsgzhcxy ljxyxydfq" +
	      "qjjfxdhctxjyrxysqtjxyebyyssyxjxncyzxfxmsyszxy schs" +
	      "hxzzzgzcgfjdltynpzgyjyztyqzpbxcbdztzc zyxxyhhsqxsh" +
	      "dhgqhjhgxwsztmmlhyxgcbtclzkkwjzrclekxtdbcykqqsayxc" +
	      "jxwwgsbhjyzs  csjkqcxswxfltynytpzc czjqtzwjqdzzzqz" +
	      "ljjxlsbhpyxxpsxshheztxfptjqyzzxhyaxncfzyyhxgnxmywx" +
	      "tcspdhhgymxmxqcxtsbcqsjyxxtyyly pclmmszmjzzllcogxz" +
	      "aajzyhjmzxhdxzsxzdzxleyjjzjbhzmzzzqtzpsxztdsxjjlny" +
	      "azhhyysrnqdthzhayjyjhdzjzlsw cltbzyecwcycrylcxnhzy" +
	      "dzydtrxxbzsxqhxjhhlxxlhdlqfdbsxfzzyychtyyjbhecjkgj" +
	      "fxhzjfxhwhdzfyapnpgnymshk mamnbyjtmxyjcthjbzyfcgty" +
	      "hwphftwzzezsbzegpbmtskftycmhbllhgpzjxzjgzjyxzsbbqs" +
	      "czzlzccstpgxmjsftcczjz djxcybzlfcjsyzfgszlybcwzzby" +
	      "zdzypswyjgxzbdsysxlgzybzfyxxxccxtzlsqyxzjqdcztdxzj" +
	      "jqcgxtdgscxzsyjjqcc ldqztqchqqjzyezwkjcfypqtynlmkc" +
	      "qzqzbqnyjddzqzxdpzjcdjstcjnxbcmsjqmjqwwjqnjnlllwqc" +
	      "qqdzpzydcydzcttf znztqzdtjlzbclltdsxkjzqdpzlzntjxz" +
	      "bcjltqjldgdbbjqdcjwynzyzcdwllxwlrxntqqczxkjld tdgl" +
	      " lajjkly kqll dz td ycggjyxdxfrskstqdenqmrkq  hgkd" +
	      "ldazfkypbggpzrebzzykyqspegjjglkqzzzslysywqzwfqzylz" +
	      "zlzhwcgkyp qgnpgblplrrjyxcccyyhsbzfybnyytgzxylxczw" +
	      "h zjzblfflgskhyjzeyjhlplllldzlyczblcybbxbcbpnnzc r" +
	      " sycgyy qzwtzdxtedcnzzzty hdynyjlxdjyqdjszwlsh lbc" +
	      "zpyzjyctdyntsyctszyyegdw ycxtscysmgzsccsdslccrqxyy" +
	      "elsm xztebblyylltqsyrxfkbxsychbjbwkgskhhjh xgnlycd" +
	      "lfyljgbxqxqqzzplnypxjyqymrbsyyhkxxstmxrczzywxyhymc" +
	      "l lzhqwqxdbxbzwzmldmyskfmklzcyqyczqxzlyyzmddz ftqp" +
	      "czcyypzhzllytztzxdtqcy ksccyyazjpcylzyjtfnyyynrs y" +
	      "lmmnxjsmyb sljqyldzdpqbzzblfndsqkczfywhgqmrdsxycyt" +
	      "xnq jpyjbfcjdyzfbrxejdgyqbsrmnfyyqpghyjdyzxgr htk " +
	      "leq zntsmpklbsgbpyszbydjzsstjzytxzphsszsbzczptqfzm" +
	      "yflypybbjgxzmxxdjmtsyskkbzxhjcelbsmjyjzcxt mljshrz" +
	      "zslxjqpyzxmkygxxjcljprmyygadyskqs dhrzkqxzyztcghyt" +
	      "lmljxybsyctbhjhjfcwzsxwwtkzlxqshlyjzjxe mplprcglt " +
	      "zztlnjcyjgdtclklpllqpjmzbapxyzlkktgdwczzbnzdtdyqzj" +
	      "yjgmctxltgcszlmlhbglk  njhdxphlfmkyd lgxdtwzfrjejz" +
	      "tzhydxykshwfzcqshknqqhtzhxmjdjskhxzjzbzzxympagjmst" +
	      "bxlskyynwrtsqlscbpspsgzwyhtlksssw hzzlyytnxjgmjszs" +
	      "xfwnlsoztxgxlsmmlbwldszylkqcqctmycfjbslxclzzclxxks" +
	      "bjqclhjpsqplsxxckslnhpsfqqytxy jzlqldtzqjzdyydjnzp" +
	      "d cdskjfsljhylzsqzlbtxxdgtqbdyazxdzhzjnhhqbyknxjjq" +
	      "czmlljzkspldsclbblzkleljlbq ycxjxgcnlcqplzlznjtzlx" +
	      "yxpxmyzxwyczyhzbtrblxlcczjadjlmmmsssmybhb kkbhrsxx" +
	      "jmxsdynzpelbbrhwghfchgm  klltsjyycqltskywyyhywxbxq" +
	      "ywbawykqldq tmtkhqcgdqktgpkxhcpthtwthkshthlxyzyyda" +
	      "spkyzpceqdltbdssegyjq xcwxssbz dfydlyjcls yzyexcyy" +
	      "sdwnzajgyhywtjdaxysrltdpsyxfnejdy lxllqzyqqhgjhzyc" +
	      "shwshczyjxllnxzjjn fxmfpycyawddhdmczlqzhzyztldywll" +
	      "hymmylmbwwkxydtyldjpyw xjwmllsafdllyflb   bqtzcqlj" +
	      "tfmbthydcqrddwr qnysnmzbyytbjhp ygtjahg tbstxkbtzb" +
	      "kldbeqqhqmjdyttxpgbktlgqxjjjcthxqdwjlwrfwqgwqhckry" +
	      "swgftgygbxsd wdfjxxxjzlpyyypayxhydqkxsaxyxgskqhykf" +
	      "dddpplcjlhqeewxksyykdbplfjtpkjltcyyhhjttpltzzcdlsh" +
	      "qkzjqyste eywyyzy xyysttjkllpwmcyhqgxyhcrmbxpllnqt" +
	      "jhyylfd fxzpsftljxxjbswyysksflxlpplbbblbsfxyzsylff" +
	      "fscjds tztryysyffsyzszbjtbctsbsdhrtjjbytcxyje xbne" +
	      "bjdsysykgsjzbxbytfzwgenhhhhzhhtfwgzstbgxklsty mtmb" +
	      "yxj skzscdyjrcwxzfhmymcxlzndtdh xdjggybfbnbpthfjaa" +
	      "xwfpxmyphdttcxzzpxrsywzdlybbjd qwqjpzypzjznjpzjlzt" +
	      " fysbttslmptzrtdxqsjehbzyj dhljsqmlhtxtjecxslzzspk" +
	      "tlzkqqyfs gywpcpqfhqhytqxzkrsg gsjczlptxcdyyzss qz" +
	      "slxlzmycbcqbzyxhbsxlzdltcdjtylzjyyzpzylltxjsjxhlbr" +
	      "ypxqzskswwwygyabbztqktgpyspxbjcmllxztbklgqkq lsktf" +
	      "xrdkbfpftbbrfeeqgypzsstlbtpszzsjdhlqlzpmsmmsxlqqnk" +
	      "nbrddnxxdhddjyyyfqgzlxsmjqgxytqlgpbqxcyzy drj gtdj" +
	      "yhqshtmjsbwplwhlzffny  gxqhpltbqpfbcwqdbygpnztbfzj" +
	      "gsdctjshxeawzzylltyybwjkxxghlfk djtmsz sqynzggswqs" +
	      "phtlsskmcl  yszqqxncjdqgzdlfnykljcjllzlmzjn   scht" +
	      "hxzlzjbbhqzwwycrdhlyqqjbeyfsjxwhsr  wjhwpslmssgztt" +
	      "ygyqqwr lalhmjtqjcmxqbjjzjxtyzkxbyqxbjxshzssfjlxmx" +
	      "  fghkzszggylcls rjyhslllmzxelgl xdjtbgyzbpktzhkzj" +
	      "yqsbctwwqjpqwxhgzgdyfljbyfdjf hsfmbyzhqgfwqsyfyjgp" +
	      "hzbyyzffwodjrlmftwlbzgycqxcdj ygzyyyyhy xdwegazyhx" +
	      "jlzythlrmgrxxzcl   ljjtjtbwjybjjbxjjtjteekhwslj lp" +
	      "sfyzpqqbdlqjjtyyqlyzkdksqj yyqzldqtgjj  js cmraqth" +
	      "tejmfctyhypkmhycwj cfhyyxwshctxrljhjshccyyyjltktty" +
	      "tmxgtcjtzaxyoczlylbszyw jytsjyhbyshfjlygjxxtmzyylt" +
	      "xxypzlxyjzyzyybnhmymdyylblhlsyygqllscxlxhdwkqgyshq" +
	      "ywljyyhzmsljljxcjjyy cbcpzjmylcqlnjqjlxyjmlzjqlycm" +
	      "hcfmmfpqqmfxlmcfqmm znfhjgtthkhchydxtmqzymyytyyyzz" +
	      "dcymzydlfmycqzwzz mabtbcmzzgdfycgcytt fwfdtzqssstx" +
	      "jhxytsxlywwkxexwznnqzjzjjccchyyxbzxzcyjtllcqxynjyc" +
	      "yycynzzqyyyewy czdcjyhympwpymlgkdldqqbchjxy       " +
	      "                                                  " +
	      "                 sypszsjczc     cqytsjljjt   ";


	/**
	 * 获取GBK字的拼音的首字母
	 * 由于数据较大,完整的GBK编码表按GBK规范分成3部分
	 * GBK/2为与GB2312兼容的国标汉字部分，GBK/3和GBK/4为扩展汉字部分
	 * 每一部分都有自己的地址计算公式
	 * 若输入是acsii则返回同样的acsii
	 * 若输入是中文字符则返回拼音的首字母
	 * 若输入是中文字符但是该字符不知道如何发音，则返回空字符
	 * @param hzString
	 * @return String
	 */
	public static String getGBKpy(String hzString) {
		/*
		 * 效率:处理大字符串(字符串有132055个byte,即70577个char)1000次，消耗时间44.474S.
		 */
		if (hzString == null || hzString.length() == 0)
			return "";
		int pyi, len, no;
		int ch1code = 0, ch2code = 0;
		char ch1, ch2;

		StringBuffer pyBuffer = new StringBuffer();
		byte eB[] = hzString.getBytes();
		len = eB.length;

		//开始计算
		pyi = 0;
		while (pyi < len) {
			ch1 = (char) eB[pyi];
			pyi = pyi + 1;
			ch1code = ch1;
			if (ch1code > 0 && ch1code < 129) {
				//普通的acsii
				pyBuffer.append(ch1);
				continue;
			} else {
				//GBK字符
				ch1 = (char) (256 + (int) ch1);
				if (eB[pyi] < 0) {
					ch2 = (char) (256 + (int) eB[pyi]);
				} else {
					ch2 = (char) eB[pyi];
				}
				pyi = pyi + 1;
				if (pyi > len)
					break;
			}
			ch1code = ch1;
			ch2code = ch2;
			if (ch1code <= 254 && ch1code >= 170) {
				//优先处理GB-2312汉字.
				if (ch2code > 160) {
					//查找GB-2312
					no = (ch1code - 176) * 94 + (ch2code - 160);
					pyBuffer.append(GB_2312.charAt(no - 1));
				} else {
					//查找GBK_4
					no = (ch1code - 170) * 97 + (ch2code - 63);
					pyBuffer.append(GBK_4.charAt(no - 1));
				}
			}else if (ch1code <= 160 && ch1code >= 129) {
				//查找GBK_3
				no = (ch1code - 129) * 191 + (ch2code - 63);
				pyBuffer.append(GBK_3.charAt(no - 1));
			} else {
				//不是GBK汉字
				continue;
			}
		}
		return pyBuffer.toString().trim().toLowerCase();
	}

	public static void main(String args[])
	{
		GetPy gp = new GetPy();
		try {
			String gbkStr = new String("中".getBytes("UTF-8"),"GB2312");
			System.out.println(gp.getGBKpy(gbkStr));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}