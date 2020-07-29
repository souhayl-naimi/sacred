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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
public class SacredController {

    @Autowired
    EmplacementRepository emplacementRepository;

    @Autowired
    MelangeRepository melangeRepository;

    @Autowired
    MelangeRefRepository melangeRefRepository;

    @RequestMapping(value = "/")
    public String home(){
        return "template";
    }

    @RequestMapping(value = "/consultMlgs")
    public String consultMlgs(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "10") int size,
                              @RequestParam(name = "numLot", defaultValue = "") String numLot){
        Page<Melange> melanges = melangeRepository.findByNumLotContains(numLot,PageRequest.of(page, size));
        melanges.forEach(melange -> {
            melange.setDays(90-ChronoUnit.DAYS.between(melange.getDateFabrication(), LocalDate.now()));
        });
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
    public String saveMelange(Melange melange, Long empId) {
        melangeRepository.save(melange);
        return "redirect:/consultMlgs?numLot="+melange.getNumLot();
    }

    @PostMapping(value = "deleteMelange")
    public String deleteMelange(Long id, int page, int size) {
        melangeRepository.deleteById(id);
        return "redirect:/consultMlgs?page="+page+"&size="+size;
    }

    /*----------------Emplacements------------------*/

    @RequestMapping(value = "/consultEmps")
    public String consultEmps(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "10") int size,
                              @RequestParam(name = "numEmp", defaultValue = "") String numEmp){
        Page<Emplacement> emplacements = emplacementRepository.findByNumEmplacementContainsIgnoreCase(numEmp,PageRequest.of(page, size));
        model.addAttribute("result", emplacements.getTotalElements());
        model.addAttribute("emps", emplacements.getContent());
        model.addAttribute("numEmp", numEmp);
        model.addAttribute("pages", new int[emplacements.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        return "consultEmps";
    }

    @RequestMapping(value = "/formEmp", method = RequestMethod.GET)
    public String formEmp(Model model) {
        model.addAttribute("emplacement", new Emplacement());
        return "formEmp";
    }

    @PostMapping(value = "saveEmp")
    public String saveEmp(Emplacement emplacement) {
        emplacementRepository.save(emplacement);
        return "redirect:/consultEmps?numEmp="+emplacement.getNumEmplacement();
    }

    @PostMapping(value = "deleteEmp")
    public String deleteEmp(Long id, int page, int size) {
        emplacementRepository.deleteById(id);
        return "redirect:/consultEmps?page="+page+"&size="+size;
    }

    /*----------------Réferences------------------*/

    @RequestMapping(value = "/consultRefs")
    public String consultRefs(Model model,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "10") int size,
                              @RequestParam(name = "numRef", defaultValue = "") String numRef){
        Page<MelangeRef> melangeRefs = melangeRefRepository.findByNumRefContainsIgnoreCase(numRef,PageRequest.of(page, size));
        model.addAttribute("result", melangeRefs.getTotalElements());
        model.addAttribute("refs", melangeRefs.getContent());
        model.addAttribute("pages", new int[melangeRefs.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("numRef", numRef);
        return "consultRefs";
    }

    @RequestMapping(value = "/formRef", method = RequestMethod.GET)
    public String formRef(Model model) {
        List<Emplacement> empList = emplacementRepository.findAll();
        model.addAttribute("ref", new MelangeRef());
        model.addAttribute("empList", empList);
        return "formRef";
    }

    @PostMapping(value = "saveRef")
    public String saveRef(MelangeRef melangeRef) {
        melangeRefRepository.save(melangeRef);
        return "redirect:/consultRefs?numRef="+melangeRef.getNumRef();
    }

    @PostMapping(value = "deleteRef")
    public String deleteRef(Long id, int page, int size) {
        melangeRefRepository.deleteById(id);
        return "redirect:/consultRefs?page="+page+"&size="+size;
    }
}
