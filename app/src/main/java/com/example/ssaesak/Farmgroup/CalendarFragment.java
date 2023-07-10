//package com.example.ssaesak.Farmgroup;
//
//public class CalendarFragment extends BaseFragment<FragmentCalendarBinding, CalendarViewModel>  {
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        setBinding(inflater, R.layout.fragment_calendar, container);
//        setViewModel(CalendarViewModel.class);
//
//        View view = binding.getRoot();
//
//        ArrayList<CalendarDay> calendarDayList = new ArrayList<>();
//        calendarDayList.add(CalendarDay.today());
//        calendarDayList.add(CalendarDay.from(2020, 11, 25));
//
//        EventDecorator eventDecorator = new EventDecorator(calendarDayList, getActivity(), binding.calendarTextview);
//        binding.calendarview.addDecorators(eventDecorator);
//
//
//        return view;
//    }
//
//}