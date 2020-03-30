package com.academy.HowRU.QuestionSet.viewModels;

import com.academy.HowRU.QuestionSet.dataModels.responses.CheckboxOption;
import com.academy.HowRU.QuestionSet.dataModels.responses.RadioOption;
import com.academy.HowRU.QuestionSet.dataModels.responses.ResponseOption;
import com.academy.HowRU.QuestionSet.dataModels.responses.SliderOption;
import lombok.Data;

@Data
public class ResponseOptionView {

    private Long id;

    private Integer value;

    //Slider
    private Integer min,max;
    private String min_description, max_description;

    //Radio and checkbox
    private String option;

    private ResponseOptionView(Long id, Integer value,
                               Integer min, Integer max,
                               String min_description, String max_description,
                               String option){
        this.id=id;
        this.value=value;
        this.min=min;
        this.max=max;
        this.min_description=min_description;
        this.max_description=max_description;
        this.option=option;
    }

    private ResponseOptionView(){
    }

    public static ResponseOptionView from(ResponseOption option){

        ResponseOptionView view = new ResponseOptionView();
        view.setId(option.getId());
        if(option instanceof SliderOption){
            view.setMin(((SliderOption) option).getMin());
            view.setMax(((SliderOption) option).getMax());
            view.setMin_description(((SliderOption) option).getMin_description());
            view.setMax_description(((SliderOption) option).getMax_description());
        } else if(option instanceof RadioOption){
            view.setOption(((RadioOption)option).getOption());
        } else if(option instanceof CheckboxOption){
            view.setOption(((CheckboxOption)option).getOption());
        }

        return view;

    }


}
