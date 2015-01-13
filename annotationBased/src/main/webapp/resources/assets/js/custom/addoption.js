jQuery(document).ready(function($){
    $('.my-form .add-box').click(function(){
        var n = $('.option-box').length + 1;
        if( 8 < n ) {
            alert('Stop it! you cannot give more then 8 option for a question');
            return false;
        }
        var box_html = $('<div class="option-box list-group-item my-form '+n+'"  > <div class="row"><div class="col-xs-3 col-sm-3"><label for="optionbox' + n + '">Option: <span class="box-number">'
        		+ n + '</span></label><input type="checkbox" name="listOfAnswers" value="'+n+'">'
        		+'</div>  <div class="col-xs-6 col-sm-7">  <input type="text" name="listOfOptions" class="form-control input-sm	"value="" id="optionbox'
        		+ n + '" /> </div> <div class="col-xs-6 col-sm-2"><a href="#" class="remove-box">Remove</a></div></div></div>');
        box_html.hide();
        $('.my-form div.option-box:last').after(box_html);
        box_html.fadeIn('slow');
        return false;
    });
    $('.list-group').on('click', '.remove-box', function(){
        $(this).parent().parent().css( 'background-color', '#FF6C6C' );
        $(this).parent().fadeOut("slow", function() {
            $(this).parent().parent().remove();
            $('.box-number').each(function(index){
                $(this).text( index + 1 );
            });
        });
        return false;
    });
});