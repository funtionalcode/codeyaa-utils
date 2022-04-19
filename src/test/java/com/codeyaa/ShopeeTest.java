package com.codeyaa;

import com.codeyaa.utils.common.FileUtil;
import com.codeyaa.utils.tripartite.shopee.v1.ShopeeUtil;
import com.codeyaa.utils.tripartite.tencent.impl.TencentUtilImpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ShopeeTest {
    public static void main(String[] args) throws IOException {
        download();
    }

    private static void look() {
        ShopeeUtil shopeeUtil = new ShopeeUtil();
        String s = shopeeUtil.lookBillPdf("https://partner.shopeemobile.com/api/v1/logistics/download_shipping_document?token=cUNidUZZY2Z0NDlQSEd5cku18gj%2Bi7Hs8iSIWj3EtcgUdFm6KHHQuElyQKENlM6vpKD0WBs9cp6MeiA6l%2F9%2BGJJ18niV65lSanYGh0Yb9HD%2FXogAyhldg6CYzqdieRk9vTuM5pTMWs%2BGSXIhEnc1MyBH9lAQDX2HJcF4NMCuMLxcqGnQOFiYL39rhV7tBgWFb0kzZ6kIUnxfjXV9fEHsQ%2FgaIe%2FN14%2FUq3ncr6mtg9ujdSnxgYmDSxBTY7zc%2B7cHT1EPZqdO45oAb3FMFl0Q2pVy9z0yGp7rCHzb5lj7xFL%2FEe1K5VQixt1BTVcjq%2FpYB8%2FYDPHS%2BcTMxGiPP1XwVg%3D%3D");
        System.out.println("s = " + s);

    }

    private static void download() throws IOException {
        TencentUtilImpl tencentUtil = new TencentUtilImpl();
        String url = "https://jisushopee-user-1301887866.cos.ap-guangzhou.myqcloud.com/user/18615293765/29fb90a542044277efd6c53712c2c195.pdf";
        InputStream bill = tencentUtil.getBill(url);
        String s = FileUtil.readStream(bill);
        System.out.println("s = " + s);
//        FileUtils.copyInputStreamToFile(bill, new File("d:/bill.pdf"));
    }

    private static void downloadFile() throws IOException {
        TencentUtilImpl tencentUtil = new TencentUtilImpl();
        String url = "https://partner.shopeemobile.com/api/v1/logistics/download_shipping_document?token=cUNidUZZY2Z0NDlQSEd5crsDYN7priTe2cr50RW1xwF8bEvWyC%2Bpuqco2yMi4cdKDVXjcbvFgG2%2FBDKtK4aB5sXhDbrQ4%2FYhURWYl4aWsHMBRaThyFvTv97fpwSceSIgfF0LCKdlGlTBGS9JE4W2spq%2BQaBIblzxnHa1Y3TwU2S5B5O%2BnuOb3q2x0V9u5AYTKMaSiA69M419rdJgW0fupMeI8UX2gQHWBmzVCuQnQcAssCQo%2BN4CMthE6j5omjDxJ94UU6YZQCN2wfl5ZBMuak%2By40u9Li5vwU6kh18GGuMBTjmZtKFPImBZFi1pEBI6ydssnqoLmrZRicmrLMXueg%3D%3D";
        File bill = tencentUtil.getBillFile(url);
    }
}
