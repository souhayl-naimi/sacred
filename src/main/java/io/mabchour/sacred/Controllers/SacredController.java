package io.mabchour.sacred.Controllers;

import io.mabchour.sacred.Entities.Emplacement;
import io.mabchour.sacred.Entities.Melange;
import io.mabchour.sacred.Entities.MelangeRef;
import io.mabchour.sacred.Repositories.EmplacementRepository;
import io.mabchour.sacred.Repositories.MelangeRefRepository;
import io.mabchour.sacred.Repositories.MelangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SacredController {

    @Autowired
    EmplacementRepository emplacementRepository;

    @Autowired
    MelangeRepository melangeRepository;

    @Autowired
    MelangeRefRepository melangeRefRepository;

    @RequestMapping(value = "/emps")
    public String emplacements(Model model,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "size", defaultValue = "3") int size,
                               @RequestParam(name = "num", defaultValue = "") String num){
        Page<Emplacement> emplacementPage = emplacementRepository.findByNumEmplacementContainsIgnoreCase(num,PageRequest.of(page, size));
        model.addAttribute("result", emplacementPage.getTotalElements());
        model.addAttribute("empList", emplacementPage.getContent());
        model.addAttribute("pages", new int[emplacementPage.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("num", num);
        model.addAttribute("size", size);
        return "emplacements";
    }

    @RequestMapping(value = "/refs")
    public String emplacements(Model model,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "size", defaultValue = "3") int size,
                              Long id){
        Page<MelangeRef> melangeRefs = melangeRefRepository.findByEmplacement_Id(id,PageRequest.of(page, size));
        model.addAttribute("result", melangeRefs.getTotalElements());
        model.addAttribute("melangeRefs", melangeRefs.getContent());
        model.addAttribute("pages", new int[melangeRefs.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        return "references";
    }

    @RequestMapping(value = "/mlgs")
    public String emplacements(Model model,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "size", defaultValue = "3") int size,
                               Long idEmp,
                               Long idRef){
        Page<Melange> melanges = melangeRepository.findByMelangeRef_Emplacement_IdAndMelangeRef_Id(idEmp,idRef,PageRequest.of(page, size));
        model.addAttribute("result", melanges.getTotalElements());
        model.addAttribute("melanges", melanges.getContent());
        model.addAttribute("pages", new int[melanges.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        return "melanges";
    }

    @RequestMapping(value = "/consultMlgs")
    public String consultMlgs(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "10") int size,
                              @RequestParam(name = "numLot", defaultValue = "") String numLot){
        Page<Melange> melanges = melangeRepository.findByNumLotContains(numLot,PageRequest.of(page, size));
        model.addAttribute("result", melanges.getTotalElements());
        model.addAttribute("melanges", melanges.getContent());
        model.addAttribute("pages", new int[melanges.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("numLot", numLot);
        return "consultMlgs";
    }
    @RequestMapping(value = "/formMelange", method = RequestMethod.GET)
    public String formMelange(Model model) {
        List<MelangeRef> refList = melangeRefRepository.findAll();
        List<Emplacement> empList = emplacementRepository.findAll();
        model.addAttribute("melange", new Melange());
        model.addAttribute("refList", refList);
        model.addAttribute("empList", empList);
        return "formMelange";
    }

    @PostMapping(value = "saveMelange")
    public String saveMelange(Melange melange, Model model,String empName) {
        model.addAttribute("saved", melange);
        melange.getMelangeRef().getEmplacement().setNumEmplacement(empName);
        melangeRepository.save(melange);
        return "consultMlgs";

    }
}
