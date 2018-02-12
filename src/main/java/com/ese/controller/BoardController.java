package com.ese.controller;

import java.io.File;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ese.domain.BoardVO;
import com.ese.domain.Criteria;
import com.ese.domain.PageMaker;
import com.ese.service.BoardService;

/**
 * Board Controller
 * 
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET(BoardVO board, Model model) throws Exception{
		
		logger.info("register get................");
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardVO board, RedirectAttributes rttr) throws Exception{
		
		logger.info("regist post.........");
		logger.info(board.toString());
		
		service.regist(board);
		
		//model.addAttribute("result", "success");
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		//return "/board/success";  //���ΰ�ħ�� �̿��� ���踦 �����ϱ� ���� redirect�� �̿��Ͽ� ��� ��ȸ �������� �ڵ� �̵� 
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value="/listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception{
		
		logger.info("show all list...............");
		model.addAttribute("list", service.listAll());
	}
	
	@RequestMapping(value="/read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model) throws Exception{
		
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception {

		logger.info("Remove...............");
		service.remove(bno);
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public void modifyGET(int bno, Model model) throws Exception{
		
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyPOST(BoardVO board, RedirectAttributes rttr) throws Exception{
		
		logger.info("mod post........");
		
		service.modify(board);
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value= "/listCri", method=RequestMethod.GET)
	public void listAll(Criteria cri, Model model) throws Exception {
		
		logger.info("show list Page with Criteria.........");
		
		model.addAttribute("list", service.listCriteria(cri));
	}
	
	// ����¡ ó�� 
	@RequestMapping(value="/listPage", method = RequestMethod.GET)
	public void listPage(@ModelAttribute("cri") Criteria cri, Model model) throws Exception{
		
		logger.info(cri.toString());
		
		model.addAttribute("list", service.listCriteria(cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCountCriteria(cri));
		
		model.addAttribute("pageMaker", pageMaker);
	}
	
	//��ȸ ������ ���� (��ȸ ������ ���޵Ǵ� �Ķ���Ͱ� bno, page, perPageNum���� �þ)
	@RequestMapping(value="/readPage", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception{
		
		model.addAttribute(service.read(bno));
	}
	
	// ���� ����¡ ó��
	@RequestMapping(value="/removePage", method=RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno,
			Criteria cri,
			RedirectAttributes rttr) throws Exception {

		logger.info("RemovePage...............");
		
		service.remove(bno);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public void modifyPagingGET(@RequestParam("bno") int bno, 
			@ModelAttribute("cri") Criteria cri, 
			Model model) throws Exception{
		
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value="/modifyPage", method = RequestMethod.POST)
	public String modifyPagingPOST(BoardVO board, Criteria cri, RedirectAttributes rttr) throws Exception{
		
		service.modify(board);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/board/listPage";
	}
	
	/**
	 * Download Excel File
	 * @Method Name : excelDown
	 * @create Date : 2018. 02. 08.
	 * @made by : Yun Young
	 * @param :
	 * @return : String
	 */
	@RequestMapping(value = "/excelDown.do")
	public @ResponseBody String excelDown(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//String filePath = request.getSession().getServletContext().getRealPath("/")+"file_upload";
		String filePath = "C://zzz/" + "file_download";
		
		System.out.println(filePath);
		
		File file = new File(filePath);
		boolean fileMKResult = false;
		
		if(!file.exists()){
			fileMKResult = file.mkdir();
		}
		
		if(fileMKResult){
			System.out.println("Directory is made");
		}
		
		service.excelDown(response, filePath);
		
		return null;
	}
	
}
